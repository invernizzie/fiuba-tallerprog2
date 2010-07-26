package model.filters;

import java.util.HashMap;
import java.util.Map;

import control.command.CommandApplyFilter;
import model.filters.impl.Binarize;
import model.filters.impl.Contrast;
import model.filters.impl.Grayscale;
import model.filters.impl.Invert;

import control.Constants;
import control.FilterEnum;
import control.MasksEnum;

public class FilterMap {

	Map<String, CommandApplyFilter> map;

	public FilterMap() {
		map = new HashMap<String, CommandApplyFilter>();
		map.put(MasksEnum.BLUR.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.BLUR)));
		map.put(MasksEnum.SHARPEN.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.SHARPEN)));
		map.put(MasksEnum.LOW_PASS.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.LOW_PASS)));
		map.put(MasksEnum.MID_PASS.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.MID_PASS)));
        map.put(MasksEnum.LAPLACIAN.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.LAPLACIAN)));
		map.put(MasksEnum.PREWITT_1.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.PREWITT_1)));
		map.put(MasksEnum.PREWITT_2.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.PREWITT_2)));
		map.put(MasksEnum.GAUSS_LOW_PASS.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.GAUSS_LOW_PASS)));
		map.put(MasksEnum.SMOOTH.getNombre(), new CommandApplyFilter(Constants.getMaskFilter(MasksEnum.SMOOTH)));
		map.put(FilterEnum.INVERT.getNombre(), new CommandApplyFilter( new Invert() ));
		map.put(FilterEnum.CONTRAST.getNombre(), new CommandApplyFilter( new Contrast() ));
		map.put(FilterEnum.GREY_SCALE.getNombre(), new CommandApplyFilter( new Grayscale() ));
		map.put(FilterEnum.BINARIZE.getNombre(), new CommandApplyFilter( new Binarize() ));
	}
	
	public CommandApplyFilter getFilterCommand(String key){
		return map.get(key);
	}
}
