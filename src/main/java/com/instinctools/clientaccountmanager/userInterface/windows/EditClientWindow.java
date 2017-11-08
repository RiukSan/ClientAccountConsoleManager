package com.instinctools.clientaccountmanager.userInterface.windows;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.TextInputDialog;
import com.instinctools.clientaccountmanager.model.Account;
import com.instinctools.clientaccountmanager.model.Client;
import com.instinctools.clientaccountmanager.services.AccountService;
import com.instinctools.clientaccountmanager.services.ClientService;
import com.instinctools.clientaccountmanager.services.impl.AccountServiceImpl;
import com.instinctools.clientaccountmanager.services.impl.ClientServiceImpl;

public class EditClientWindow {
	public Window getEditWindow(int id) {
		Panel contentPanel = new Panel(new GridLayout(3));
		ClientService clientService = new ClientServiceImpl();
		AccountService accountService = new AccountServiceImpl();
		Client client = clientService.get(id);
		Window window = new BasicWindow("Edit " + client.getName());
		for (Account account : client.getAccounts()) {
			contentPanel.addComponent(new Label(account.getName()));
			contentPanel.addComponent(new Button("Delete", new Runnable() {
				@Override
				public void run() {
					accountService.delete(account.getId());
					WindowBasedTextGUI textGUI = window.getTextGUI();
					textGUI.removeWindow(window);
					textGUI.addWindowAndWait(new EditClientWindow().getEditWindow(id));
				}
			}).setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
			contentPanel.addComponent(new Button("Edit", new Runnable() {
				@Override
				public void run() {
					WindowBasedTextGUI textGUI = window.getTextGUI();
					String input = TextInputDialog.showDialog(textGUI, "Account " + account.getName(), "Enter new name",
							account.getName());
					accountService.update(account.getId(), input);
					textGUI.removeWindow(window);
					textGUI.addWindowAndWait(new EditClientWindow().getEditWindow(id));
				}
			}).setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
		}
		contentPanel.addComponent(new Label("Name"));
		TextBox nameBox = new TextBox();
		contentPanel.addComponent(nameBox);
		contentPanel.addComponent(new Button("Create account", new Runnable() {
			@Override
			public void run() {
				accountService.create(id, nameBox.getText());
				WindowBasedTextGUI textGUI = window.getTextGUI();
				textGUI.removeWindow(window);
				textGUI.addWindowAndWait(new EditClientWindow().getEditWindow(id));
			}
		}).setLayoutData(GridLayout.createHorizontallyEndAlignedLayoutData(1)));
		contentPanel.addComponent(new Button("Close", new Runnable() {
			@Override
			public void run() {
				window.close();
			}
		}));
		contentPanel.addComponent(new Button("Return", new Runnable() {
			@Override
			public void run() {
				WindowBasedTextGUI textGUI = window.getTextGUI();
				textGUI.removeWindow(window);
				textGUI.addWindowAndWait(new StartWindow().getStartWindow());
			}
		}));
		window.setComponent(contentPanel);
		return window;
	}
}
