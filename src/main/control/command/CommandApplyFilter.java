package main.control.command;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import main.model.filters.Filter;
import main.control.command.exceptions.CommandExecutionException;

/**
 * @author Esteban I. Invernizzi
 * @created 13/04/2010
 */
public class CommandApplyFilter extends MyFrameCommand {

    Filter filter;

    protected CommandApplyFilter() {}

    public CommandApplyFilter(Filter filter) {
        this.filter = filter;
    }

    protected void doExecute() throws CommandExecutionException {
        Image img = filter.filter(frame.getImage());
        frame.setImage(img);
        frame.repaint();
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public static String[] extractFilterNames(String sequence) {
        boolean defaultParameters = false;
    	StringTokenizer tokenizer = new StringTokenizer(sequence, "-");
    	List<String> filters = new ArrayList<String>();
    	while(tokenizer.hasMoreTokens()){
    		String token = tokenizer.nextToken();
    		if(!"true".equals(token) && !"false".equals(token)) {
    			filters.add(token);
    		}
    	}
        return filters.toArray(new String[1]);
    }

    public static boolean determineParametricsChoice(String sequence) {
        boolean defaultParameters = false;
    	StringTokenizer tokenizer = new StringTokenizer(sequence, "-");
    	List<String> filters = new ArrayList<String>();
    	while(tokenizer.hasMoreTokens()){
            if (tokenizer.nextToken().equals("true")){
    			return true;
    		}

    	}
        return false;
    }
}
