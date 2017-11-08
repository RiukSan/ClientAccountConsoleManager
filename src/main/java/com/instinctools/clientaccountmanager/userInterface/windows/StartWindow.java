package com.instinctools.clientaccountmanager.userInterface.windows;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.instinctools.clientaccountmanager.model.Client;
import com.instinctools.clientaccountmanager.services.ClientService;
import com.instinctools.clientaccountmanager.services.impl.ClientServiceImpl;

public class StartWindow {
	public Window getStartWindow() {
		Window window = new BasicWindow("All clients");
		Panel contentPanel = new Panel(new GridLayout(3));
		ClientService clientService = new ClientServiceImpl();
		for (Client client : clientService.getAllUsers()) {
			contentPanel.addComponent(new Label(client.getName()));
			contentPanel.addComponent(new Button("Delete", new Runnable() {
				@Override
				public void run() {
					clientService.delete(client.getId());
					WindowBasedTextGUI textGUI = window.getTextGUI();
					textGUI.removeWindow(window);
					textGUI.addWindowAndWait(new StartWindow().getStartWindow());
				}
			}).setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
			contentPanel.addComponent(new Button("Edit", new Runnable() {
				@Override
				public void run() {
					WindowBasedTextGUI textGUI = window.getTextGUI();
					textGUI.removeWindow(window);
					textGUI.addWindowAndWait(new EditClientWindow().getEditWindow(client.getId()));
				}
			}).setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
		}
		contentPanel.addComponent(new Label("Name"));
		TextBox nameBox = new TextBox();
		contentPanel.addComponent(nameBox);
		contentPanel.addComponent(new Button("Create client", new Runnable() {
			@Override
			public void run() {
				clientService.create(nameBox.getText());
				WindowBasedTextGUI textGUI = window.getTextGUI();
				textGUI.removeWindow(window);
				textGUI.addWindowAndWait(new StartWindow().getStartWindow());
			}
		}).setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
		contentPanel.addComponent(new Button("Close", new Runnable() {
			@Override
			public void run() {
				window.close();
			}
		}));
		window.setComponent(contentPanel);
		return window;
	}
}
