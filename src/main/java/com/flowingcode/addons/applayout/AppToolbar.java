package com.flowingcode.addons.applayout;

/*-
 * #%L
 * App Layout Addon
 * %%
 * Copyright (C) 2018 - 2019 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.ArrayList;
import java.util.List;

import com.flowingcode.addons.applayout.menu.MenuItem;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;

/**
 * Component that renders the app toolbar
 * 
 * @author mlopez
 *
 */
@SuppressWarnings("serial")
@Tag("app-toolbar")
@HtmlImport("bower_components/app-layout/app-toolbar/app-toolbar.html")
public class AppToolbar extends Component implements HasComponents {
	
	private PaperIconButton menu;
	private Component ctitle;
	private Div divTitle;
	
    public AppToolbar(String title, AppDrawer drawer) {
    	this(null,title, drawer);
    }

    public AppToolbar(Image logo, String title, AppDrawer drawer) {
    	menu = new PaperIconButton("menu");
    	menu.getElement().setAttribute("onclick", "" + drawer.getId().get() + ".toggle()");
    	this.add(menu);
    	if (logo!=null) {
    		ctitle = logo;
    		this.add(ctitle);
    	}
    	divTitle = new Div();
    	divTitle.getElement().setAttribute("main-title", true);
    	this.add(divTitle);
    	setTitle(title);
    }

    public void setTitle(String title) {
    	divTitle.setText(title);
    }

	public void setToolbarIconButtons(MenuItem[] menuItems) {
		List<PaperIconButton> toolbarIconButtons = createToolbarIconButtons(menuItems);
		this.removeAll();
		this.add(menu);
		if (ctitle!=null) this.add(ctitle);
		if (divTitle!=null) this.add(divTitle);
		toolbarIconButtons.forEach(tib->this.add(tib));
	}

	private List<PaperIconButton> createToolbarIconButtons(MenuItem[] menuItems) {
        List<PaperIconButton> result = new ArrayList<>();
        for (MenuItem menuItem : menuItems) {
                PaperIconButton paperIconButton = new PaperIconButton(menuItem.getIcon(),menuItem.getCommand());
               	paperIconButton.setEnabled(menuItem.isEnabled());
                menuItem.setRefreshCallback(() -> paperIconButton.setIcon(menuItem.getIcon()));
                result.add(paperIconButton);
        }
        return result;
}

	public void setMenuIconVisible(boolean visible) {
		menu.setVisible(visible);
	}

    
}
