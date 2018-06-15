package zhf.action;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;

import com.opensymphony.xwork2.ActionSupport;

import zhf.logic.GhPlanFactLogic;
import zhf.table.GhTargetTable;
import zhf.util.StringUtil;

public class CreateJFreeChartBarAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    private JFreeChart chart;

    public JFreeChart getChart() {
	// System.out.println("chart action begins");
	// 步骤1：创建CategoryDataset对象（准备数据）
	CategoryDataset dataset = createDataset();
	// System.out.println("dataset created");
	// 步骤2：根据Dataset 生成JFreeChart对象，以及做相应的设置
	chart = createChart(dataset);
	// System.out.println("chart created");
	// 步骤3：将JFreeChart对象输出到文件，Servlet输出流等
	String saveFileFolder = "";
	try {
	    saveFileFolder = StringUtil.getWebRootPath();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	saveAsFile(chart, saveFileFolder + "images\\bar.png", 780, 450);
	// System.out.println("file output");
	return chart;
    }

    // 保存为文件
    public static void saveAsFile(JFreeChart chart, String outputPath, int weight, int height) {
	FileOutputStream out = null;
	try {
	    File outFile = new File(outputPath);
	    if (!outFile.getParentFile().exists()) {
		outFile.getParentFile().mkdirs();
	    }
	    out = new FileOutputStream(outputPath);
	    // 保存为PNG文件
	    ChartUtilities.writeChartAsPNG(out, chart, weight, height);
	    out.flush();
	    out.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    // do nothing
	}
    }

    // 根据CategoryDataset生成JFreeChart对象
    public JFreeChart createChart(CategoryDataset categoryDataset) {
	JFreeChart jfreechart = ChartFactory.createBarChart("※计划与实际图表", // 标题
		"", // categoryAxisLabel （category轴，横轴，X轴的标签）
		"", // valueAxisLabel（value轴，纵轴，Y轴的标签）
		categoryDataset, // dataset
		PlotOrientation.VERTICAL, true, // legend
		false, // tooltips
		false); // URLs

	// 以下的设置可以由用户定制，也可以省略
	jfreechart.setTitle(new TextTitle(this.getText("label.recruimentPlanFact"), new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 22)));
	CategoryPlot plot = (CategoryPlot) jfreechart.getPlot();
	plot.setBackgroundPaint(Color.WHITE);
	jfreechart.getLegend().setItemFont(new Font("黑体", Font.ITALIC, 12));
	// 数据轴精度
	NumberAxis na = (NumberAxis) plot.getRangeAxis();
	na.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	// 坐标轴位置
	plot.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
	// 柱体属性
	BarRenderer renderer = (BarRenderer) plot.getRenderer();
	// 每个BAR之间的间隔
	renderer.setItemMargin(0.0f);
	// 每个BAR的最大宽度
	renderer.setMaximumBarWidth(0.03);
	// 显示条目标签
	renderer.setBaseItemLabelsVisible(true);
	// 设置条目标签生成器,在JFreeChart1.0.6之前可以通过renderer.setItemLabelGenerator(CategoryItemLabelGenerator
	// generator)方法实现，但是从版本1.0.6开始有下面方法代替
	// renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	// renderer.setItemLabelsVisible(true);
	// renderer.setBaseSeriesVisible(false);
	// 设置条目标签显示的位置,outline表示在条目区域外,baseline_center表示基于基线且居中
	renderer.setBasePositiveItemLabelPosition(
		new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
	// 取消柱子的倒影
	renderer.setShadowVisible(false);
	// 柱体颜色设置
	renderer.setSeriesPaint(0, Color.decode("#EEEE00"));
	renderer.setSeriesPaint(1, Color.decode("#00FF00"));
	renderer.setSeriesPaint(2, Color.decode("#FF0000"));
	plot.setRenderer(renderer);
	// 背景色 透明度
	plot.setBackgroundAlpha(0.0f);
	// 前景色 透明度
	plot.setForegroundAlpha(0.5f);
	// 其它设置可以参考 CategoryPlot

	return jfreechart;
    }

    /**
     * 创建CategoryDataset对象
     * 
     */
    public static CategoryDataset createDataset() {

	GhPlanFactLogic lgc = new GhPlanFactLogic();
	/*
	 * 图片数据
	 */
	// 下方数据
	String[] rowKeys = { "计划", "实际", "差" };
	// String []colKeys = {"Aflac&Mitsui", "Metlife", "SJNK", "TMNF"};
	@SuppressWarnings("rawtypes")
	List list1 = lgc.getUserList();
	// x轴数据
	String colKeys[] = new String[list1.size()];
	int ghhcs[] = new int[list1.size()];
	for (int i = 0; i < list1.size(); i++) {
	    GhTargetTable gt = (GhTargetTable) list1.get(i);
	    colKeys[i] = gt.getPjname();
	}
	/*
	 * double [][] data = { {9, 16, 4, 19}, {5, 10D, 2, 13} };
	 */
	double ghrecs[] = new double[list1.size()];
	// 柱体数据
	double[][] data = new double[3][list1.size()];
	
	for (int i = 0; i < list1.size(); i++) {
	    GhTargetTable gt = (GhTargetTable) list1.get(i);
	    ghhcs[i] = gt.getGhhcs();
	    data[0][i] = ghhcs[i];
	    ghrecs[i] = lgc.getGhactualcsRecs(colKeys[i]);
	    data[1][i] = ghrecs[i];
	    data[2][i] = data[0][i] - data[1][i];
	}

	return DatasetUtilities.createCategoryDataset(rowKeys, colKeys, data);

	/*
	 * 也可以使用以下代码 DefaultCategoryDataset categoryDataset = new
	 * DefaultCategoryDataset(); categoryDataset.addValue(10, "rowKey", "colKey");
	 * return categoryDataset;
	 */
    }

}