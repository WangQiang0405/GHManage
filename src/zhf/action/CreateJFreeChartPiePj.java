package zhf.action;

import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import com.opensymphony.xwork2.ActionSupport;

import zhf.logic.GhPlanFactLogic;

public class CreateJFreeChartPiePj extends ActionSupport {
    public JFreeChart getChart() {
	PieDataset dataset = createDataset();
	JFreeChart chart = createPie2DChart(dataset);
	return chart;
    }

    /**
    *
    */
    private static final long serialVersionUID = 1L;
    // 中文乱码
    public static final Font font = new Font("宋体", Font.BOLD, 13);

    private static PieDataset createDataset() {
	
	GhPlanFactLogic lgc = new GhPlanFactLogic();
	DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
	double aflac_mitsui = lgc.getGhactualcsRecs("Aflac&Mitsui");
	double sjnk = lgc.getGhactualcsRecs("SJNK");
	double tmnf = lgc.getGhactualcsRecs("TMNF");
	double metlife = lgc.getGhactualcsRecs("MetLife");
	defaultpiedataset.setValue("Aflac&Mitsui", aflac_mitsui);
	defaultpiedataset.setValue("SJNK", sjnk);
	defaultpiedataset.setValue("TMNF", tmnf);
	defaultpiedataset.setValue("MetLife", metlife);
	/*
	 * defaultpiedataset.setValue("Five", new Double(11.0));
	 * defaultpiedataset.setValue("Six", new Double(19.4));
	 */
	return defaultpiedataset;
    }

    private static JFreeChart createPie2DChart(PieDataset piedataset) {
	JFreeChart jfreechart = ChartFactory.createPieChart("项目比例", piedataset, true, true, false);
	// 得到标题
	TextTitle texttitle = jfreechart.getTitle();
	// 标题
	texttitle.setFont(font);
	texttitle.setToolTipText("A title tooltip!");
	// 得到绘图区
	PiePlot pieplot = (PiePlot) jfreechart.getPlot();
	// 设置标签字体
	pieplot.setLabelFont(font);
	pieplot.setNoDataMessage("No data available");
	pieplot.setCircular(false);
	pieplot.setLabelGap(0.02);
	pieplot.setSectionPaint("Aflac&Mitsui", Color.blue);
	pieplot.setSectionPaint("SJNK", Color.red);
	pieplot.setSectionPaint("TMNF", Color.yellow);
	pieplot.setSectionPaint("MetLife", Color.decode("#00FF00"));
	// 提示条字体
	jfreechart.getLegend().setItemFont(font);
	pieplot.setBackgroundPaint(Color.decode("#E6E6FA"));

	DecimalFormat df = new DecimalFormat("0.0%");// 获得一个DecimalFormat对象，主要是设置小数问题
	NumberFormat nf = NumberFormat.getNumberInstance();// 获得一个NumberFormat对象
	StandardPieSectionLabelGenerator sp1 = new StandardPieSectionLabelGenerator("{0}  {2}", nf, df);// 获得StandardPieSectionLabelGenerator对象
	pieplot.setLabelGenerator(sp1);// 设置饼图显示百分比
	return jfreechart;
    }
}