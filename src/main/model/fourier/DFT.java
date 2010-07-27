package main.model.fourier;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class DFT {
	
	private int numberOfPoints;      
	private List<Point> data;  

	public DFT(List<Point> data, int numberOfPoints){
		this.data = data;
		this.numberOfPoints = numberOfPoints;
		if (numberOfPoints > data.size()){
			completeList();
		}
	}

	private void completeList(){
		int dif = numberOfPoints - data.size();
		int intersectionPoint = data.size()/2;
		List<Point> newList = new ArrayList<Point>();
		int i = 0;
		for (i = 0; i < intersectionPoint; i++) {
			newList.add(i, data.get(i));			
		}
		int ref = i;
		for (int j = 0; j < dif; j++,i++) {
			newList.add(i, new Point(0,0));
		}
		for (int k = ref; k < data.size(); k++,i++) {
			newList.add(i, data.get(k));
		}
		data = newList;
	}
	
	public Complex getDFTPoint(int pointNumber){
		Complex cx = new Complex();
	
		if (pointNumber >= 0 && pointNumber < numberOfPoints){
			double R = 0f;
			double I = 0f;
	
			if (pointNumber == 0){
				Point p;
				for (int n = 0; n < numberOfPoints; n++){
					p = (Point)data.get( n );
					R = R + p.getY();
				}
			}
			else{
				double x;
				double scale;
				Point p;
				for (int n = 0; n < numberOfPoints; n++){
					p = (Point)data.get( n );
					x = p.getY();
					scale = (2 * Math.PI * n * pointNumber)/numberOfPoints;
					R = R + x * Math.cos( scale );
					I = I - x * Math.sin( scale );
				}
			}
			cx.setReal( (float)R );
			cx.setImaginary( (float)I );
		}
		return cx;
	}

}
