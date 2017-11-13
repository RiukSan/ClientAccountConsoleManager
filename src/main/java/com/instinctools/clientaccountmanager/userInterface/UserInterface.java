package com.instinctools.clientaccountmanager.userInterface;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.BasicConfigurator;

import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.instinctools.clientaccountmanager.userInterface.windows.*;

public class UserInterface {

	public static void main(String[] args) {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
		Screen screen = null;
		try {
			screen = terminalFactory.createScreen();
			screen.startScreen();
			WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
			final Window window = new StartWindow().getStartWindow();
			textGUI.addWindowAndWait(window);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (screen != null) {
				try {
					screen.stopScreen();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
