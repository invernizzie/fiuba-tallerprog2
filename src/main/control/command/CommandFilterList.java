package main.control.command;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import main.model.filters.Filter;
import main.model.filters.FilterMap;
import main.model.filters.ParametricFilter;
import main.control.command.exceptions.CommandExecutionException;


public class CommandFilterList extends MyFrameCommand {

    Filter[] filters;
    FilterMap filterMap;
    boolean useDefaults;
    Filter currentFilter;

    protected CommandFilterList() {
    	filterMap = new FilterMap();
    }

    public CommandFilterList(Filter[] filters) {
        this.filters = filters;
    	filterMap = new FilterMap();
    }


    public void setUseDefaults(boolean useDefaults) {
		this.useDefaults = useDefaults;
	}
    
	protected void doExecute() throws CommandExecutionException {
        frame.setImage(frame.getImageOrig());
        //frame
        frame.repaint();
        for (int i = 0; i < filters.length; i++) {
        	currentFilter = filters[i];
				
			if(!useDefaults){
				if(currentFilter instanceof ParametricFilter){
					ParametricFilter parametricFilter = (ParametricFilter)currentFilter;	
					showSlider(parametricFilter);
				}
			}
        	
        	Image img = currentFilter.filter(frame.getImage());
        	frame.setImage(img);
        	frame.repaint();
		}
    }

	
	private void showSlider(final ParametricFilter filter) {
        final Dialog d = new Dialog(frame, "Parametrizar " + filter.getFilterName(), true);
        d.setResizable(false);
        d.setSize(new Dimension(220,120));
        d.setLocation(new Point(550,350));
        d.setLayout(new FlowLayout());
        Button btnAplicar = new Button("Aplicar");

        final JSlider slider = 
        		new JSlider((int)filter.getBottomLimit(), 
        					(int)filter.getTopLimit(), 
        					(int)filter.getDefaultValue());
        slider.setMajorTickSpacing((int)filter.getTopLimit()/5);
        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        slider.setPaintTicks(true);
               
        slider.addChangeListener(new ChangeListener() {
             public void stateChanged( ChangeEvent e )
             {
            	 filter.setValue(slider.getValue());
             }
	    });
        
        btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String arg = (String)ae.getActionCommand();
				if(arg.equals("Aplicar")){
					d.dispose();					
				}
			}
        });
        
        d.add(slider);
        d.add(btnAplicar);
        d.setVisible(true);
	}

	public Filter[] getFilters() {
		return filters;
	}

	public void setFilters(Filter[] filters) {
		this.filters = filters;
	}

	public FilterMap getFilterMap() {
		return filterMap;
	}

	public void setFilterMap(FilterMap filterMap) {
		this.filterMap = filterMap;
	}




}
