package control.command;

import control.FilterSelectorHandler;
import control.command.exceptions.CommandExecutionException;


public class CommandAddFilter extends HandlerCommand {

    public void doExecute() throws CommandExecutionException {
    	if(!(this.getHandler() instanceof FilterSelectorHandler))
            throw new CommandExecutionException(this);
    		
    	FilterSelectorHandler handler = (FilterSelectorHandler)this.getHandler();
		String[] selectedItems = handler.getListaDisponibles().getSelectedItems();
		for (int i = 0; i < selectedItems.length; i++) {
			String item = selectedItems[i];
			handler.getListaSeleccionados().add(item);
		}
    }
}
