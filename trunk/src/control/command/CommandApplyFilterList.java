package control.command;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import control.FilterSelectorHandler;
import control.command.exceptions.CommandConstructionException;
import control.command.exceptions.CommandExecutionException;


public class CommandApplyFilterList extends HandlerCommand {

    public void doExecute() throws CommandExecutionException {
    	if(!(this.getHandler() instanceof FilterSelectorHandler))
            throw new CommandExecutionException(this);
    	
    	FilterSelectorHandler handler = (FilterSelectorHandler)this.getHandler();
		handler.getDialog().setVisible(false);
		try {
			
			String[] filterNames = handler.getListaSeleccionados().getItems();
			CommandFilterList commandFilterList = CommandFactory.buildCommand(filterNames, frame, handler.getChkDefaults().getState());
			
			if(handler.getChkSaveFilter().getState()){
				saveNewFilterSequence(filterNames, handler.getChkDefaults().getState());
			}
			
			commandFilterList.execute();
			
		} catch (CommandConstructionException e) {
			handler.closeAll(e);
		} catch (CommandExecutionException e) {
			handler.closeAll(e);
		}
		
		handler.getDialog().dispose();
    }
    
	private void saveNewFilterSequence(String[] filterNames, boolean useDeaults) {
		boolean append = true;
		try
        {
			OutputStream os = new FileOutputStream("filterList.txt", append);
        	PrintStream p = new PrintStream(os);
        	p.print(useDeaults + "-");

        	String selection = "";
        	for (int i = 0; i < filterNames.length; i++) {
        		selection += filterNames[i];
        		if(i < filterNames.length - 1)
        			selection += "-";
        	}
        	List<String> filterList = new ArrayList<String>(); 
        	filterList.add(useDeaults + "-" + selection);
        	try {
				frame.loadSavedFilters(filterList);
			} catch (CommandConstructionException e) {
				System.err.println ("Error loading filters in menu");
			}
        	p.println(selection);
        	p.close();
        	os.close();
        }catch (Exception e){
            System.err.println ("Error saving filters");
        }
	}
}
