package main.control.command;

import main.control.FilterSelectorHandler;
import main.control.command.exceptions.CommandExecutionException;

public class CommandRemoveFilter extends HandlerCommand {

    public void doExecute() throws CommandExecutionException {
    	if(!(this.getHandler() instanceof FilterSelectorHandler))
            throw new CommandExecutionException(this);
    		
    	FilterSelectorHandler handler = (FilterSelectorHandler)this.getHandler();
		int selectedIndex = handler.getListaSeleccionados().getSelectedIndex();
		handler.getListaSeleccionados().remove(selectedIndex);
    }
}
