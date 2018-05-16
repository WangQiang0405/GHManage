package com.jfreechart.action;

import java.awt.Font;
import java.awt.Color;
import java.io.File;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;
import java.util.*;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartUtilities;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;  
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;  
import org.jfree.data.general.DatasetUtilities;  
import org.jfree.chart.renderer.category.BarRenderer;  
import com.opensymphony.xwork2.ActionSupport;
import zhf.GhTargetTable;

import org.jfree.chart.labels.*;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

public class CreateJFreeChartBar  extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
    
    public JFreeChart getChart(){  
    	//System.out.println("chart action begins");
        //����1������CategoryDataset����׼�����ݣ�     
        CategoryDataset dataset = createDataset();
        //System.out.println("dataset created");
        //����2������Dataset ����JFreeChart�����Լ�����Ӧ������     
        chart = createChart(dataset);     
        //System.out.println("chart created");
        //����3����JFreeChart����������ļ���Servlet�������     
        saveAsFile(chart, "C:\\apache-tomcat-8.5.23-windows-x64\\apache-tomcat-8.5.23\\webapps\\GHManage\\images\\bar.png", 780, 450);  
        //System.out.println("file output");
        return chart;
    }     
         
    //����Ϊ�ļ�     
    public static void saveAsFile(JFreeChart chart, String outputPath, int weight, int height) {     
        FileOutputStream out = null;     
        try {     
            File outFile = new File(outputPath);     
            if (!outFile.getParentFile().exists()) {     
                outFile.getParentFile().mkdirs();     
            }     
            out = new FileOutputStream(outputPath);     
            //����ΪPNG�ļ�     
            ChartUtilities.writeChartAsPNG(out, chart, weight, height);     
            //����ΪJPEG�ļ�     
            //ChartUtilities.writeChartAsJPEG(out, chart, weight, height);     
            out.flush();     
        } catch (FileNotFoundException e) {     
            e.printStackTrace();     
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            if (out != null) {     
                try {     
                    out.close();     
                } catch (IOException e) {     
                    //do nothing     
                }     
            }     
        }     
    }     
    
    //����CategoryDataset����JFreeChart����     
    public static JFreeChart createChart(CategoryDataset categoryDataset) {     
        JFreeChart jfreechart = ChartFactory.createBarChart("��ʵʱ����ͼ��",    //����     
                "",    //categoryAxisLabel ��category�ᣬ���ᣬX��ı�ǩ��     
                "",    //valueAxisLabel��value�ᣬ���ᣬY��ı�ǩ��     
                categoryDataset, // dataset     
                PlotOrientation.VERTICAL,     
                true, // legend     
                false, // tooltips     
                false); // URLs     
             
        //���µ����ÿ������û����ƣ�Ҳ����ʡ��    
        jfreechart.setTitle(new TextTitle("��ʵʱ����ͼ����λ��������", new Font("����", Font.LAYOUT_LEFT_TO_RIGHT , 22)));
        CategoryPlot  plot = (CategoryPlot) jfreechart.getPlot();    
        plot.setBackgroundPaint(Color.WHITE); 
        jfreechart.getLegend().setItemFont(new Font("����", Font.ITALIC , 12));
        //�����ᾫ�� 
        NumberAxis na= (NumberAxis)plot.getRangeAxis();  
        na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());  
        //������λ��
        plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0)); 
        //��������
        BarRenderer renderer = (BarRenderer) plot.getRenderer();   
        //ÿ��BAR֮��ļ��  
        renderer.setItemMargin(0.0f);
        //ÿ��BAR�������  
        renderer.setMaximumBarWidth(0.03);
        // ��ʾ��Ŀ��ǩ
     	renderer.setBaseItemLabelsVisible(true);
        //������Ŀ��ǩ������,��JFreeChart1.0.6֮ǰ����ͨ��renderer.setItemLabelGenerator(CategoryItemLabelGenerator
        //generator)����ʵ�֣����ǴӰ汾1.0.6��ʼ�����淽������
        //renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        //renderer.setItemLabelsVisible(true);
        //renderer.setBaseSeriesVisible(false);
		//������Ŀ��ǩ��ʾ��λ��,outline��ʾ����Ŀ������,baseline_center��ʾ���ڻ����Ҿ���
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		//ȡ�����ӵĵ�Ӱ
		renderer.setShadowVisible(false); 
		//������ɫ����
		renderer.setSeriesPaint(0, Color.decode("#EEEE00"));
		renderer.setSeriesPaint(1, Color.decode("#00FF00"));
		renderer.setSeriesPaint(2, Color.decode("#FF0000"));
        plot.setRenderer(renderer);
        //����ɫ��͸����     
        plot.setBackgroundAlpha(0.0f);     
        //ǰ��ɫ��͸����     
        plot.setForegroundAlpha(0.5f);   
        //�������ÿ��Բο� CategoryPlot     
             
        return jfreechart;     
    }     
    
    /**    
     * ����CategoryDataset����    
     *     
     */    
    public static CategoryDataset createDataset() {     
    	
    	/*ͼƬ����
    	 * */
    	//�·�����
        String []rowKeys = {"�ƻ�", "ʵ��","��"};     
        //String []colKeys = {"Aflac&Mitsui", "Metlife", "SJNK", "TMNF"};     
        @SuppressWarnings("rawtypes")
		List list1 = ChartDataAction.getUserList();
        //x������
    	String colKeys[] = new String[list1.size()]; 
    	int ghhcs[] = new int[list1.size()];
    	for(int i=0;i<list1.size();i++) {
    		GhTargetTable gt = (GhTargetTable)list1.get(i);
    		colKeys[i] = gt.getPjname();
    	}
        /*double [][] data = {     
                {9, 16, 4, 19},     
                {5, 10D, 2, 13}   
        };
        */
    	double ghrecs[] = new double[list1.size()];
    	//��������
    	double [][] data = new double[3][list1.size()];
		
		for(int i=0;i<list1.size();i++) {
    		GhTargetTable gt = (GhTargetTable)list1.get(i);
    		ghhcs[i] = gt.getGhhcs();
    		data[0][i] = ghhcs[i];
    		ghrecs[i] = ChartDataAction.getGhactualcsRecs(colKeys[i]);
			data[1][i] = ghrecs[i];
			data[2][i] = data[0][i] - data[1][i];
    	}
    	
        return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);
        
        /*Ҳ����ʹ�����´���     
        DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();     
        categoryDataset.addValue(10, "rowKey", "colKey");     
        return  categoryDataset;   
        */             
    }     
    
}  