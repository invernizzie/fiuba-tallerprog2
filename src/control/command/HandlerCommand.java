package control.command;

import java.awt.event.ActionListener;


public abstract class HandlerCommand extends MyFrameCommand {

    ActionListener handler;

	public ActionListener getHandler() {
		return handler;
	}

	public void setHandler(ActionListener handler) {
		this.handler = handler;
	}


}
