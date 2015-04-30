package com.sinosoft.gypsymoth.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * 图表工具类（3D柱状图）
 * 
 * @author lixin
 * 
 */
public class ChartUtil {

	/**
	 * 图片长度, 默认为650
	 */
	public static int IMAGE_WIDTH = 760;

	/**
	 * 图片高度, 默认为350
	 */
	public static int IMAGE_HEIGHT = 350;

	/**
	 * 设置图片显示大小,默认为450*350
	 */
	public static void setImageRange(int width, int height) {
		IMAGE_WIDTH = width;
		IMAGE_HEIGHT = height;
	}

	/**
	 * 用于生成柱状图
	 * 
	 * @param title
	 *            图形标题
	 * @param domain
	 *            横坐标名称
	 * @param range
	 *            纵坐标名称
	 * @param session
	 *            图片生命周期
	 * @param dataset
	 *            数据集
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 * @return
	 */
	public static String createBarChartChart(String title, String domain,
			String range, HttpSession session, DefaultCategoryDataset dataset,
			int width, int height) {
		String filename = null;
		// 设置长宽
		setImageRange(width, height);
		try {
			JFreeChart chart = createChart(title, domain, range, dataset);
			// 把生成的图片放到临时目录
			ChartRenderingInfo info = new ChartRenderingInfo(
					new StandardEntityCollection());
			// 设置图片名称前缀
			ServletUtilities.setTempFilePrefix("chart-");
			// 生成图片
			filename = ServletUtilities.saveChartAsPNG(chart, IMAGE_WIDTH,
					IMAGE_HEIGHT, info, session);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			filename = "error_warning.gif";
		}
		return filename;
	}

	/**
	 * Creates a sample chart.
	 * 
	 * @param title
	 *            图表标题
	 * @param domain
	 *            横坐标名称
	 * @param range
	 *            纵坐标名称
	 * @param dataset
	 *            数据集
	 * 
	 * @return The chart.
	 */
	private static JFreeChart createChart(String title, String domain,
			String range, CategoryDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createBarChart3D(title, // 图表标题
				domain, // 横坐标名称
				range, // 纵坐标名称
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：垂直（VERTICAL） 水平（HORIZONTAL）
				false, // 是否显示图例(对于简单的柱状图必须是false)
				true, // 是否生成工具
				false // 是否生成URL链接
				);
		// set the textTitle of the chart
		TextTitle textTitle = chart.getTitle();
		// 设置标题的字体，不然会中文乱码
		String fontA = "宋体";
		String fontB = "黑体";
		textTitle.setFont(new Font(fontB, Font.PLAIN, 16));

		//大背景颜色样式
		  textTitle.setBackgroundPaint(new GradientPaint(0.0F, 0.0F,
		  Color .decode("#EEF7FF"), 250F, 0.0F, Color.white, true));
		  textTitle.setExpandToFitSpace(true); chart.setBackgroundPaint(new
		  GradientPaint(0.0F, 0.0F, Color .decode("#EEF7FF"), 250F, 0.0F,
		  Color.white, true));
		 

		chart.setTitle(textTitle);
		CategoryPlot plot = (CategoryPlot) chart.getCategoryPlot();
		BarRenderer3D customBarRenderer = (BarRenderer3D) plot.getRenderer();
		// CustomBarRenderer3D customBarRenderer = new CustomBarRenderer3D();
		// BarRenderer3D customBarRenderer = new BarRenderer3D();

		//小背景颜色样式
		 plot.setDomainGridlinePaint(Color.white);
		 plot.setDomainGridlinesVisible(false);
		 plot.setRangeGridlinePaint(Color.black);
		 plot.setBackgroundPaint(Color.decode("#F2F7F8")); // 设置是否有横线
		 plot.setRangeGridlinesVisible(false);
		 

		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// 设置纵坐标名称的字体
		numberaxis.setLabelFont(new Font(fontA, Font.PLAIN, 14));
		// 设置纵坐标上显示的数字字体
		numberaxis.setTickLabelFont(new Font("Fixedsys", Font.PLAIN, 12));
		// 设置横坐标名称的字体
		CategoryAxis categoryaxis = plot.getDomainAxis();
		categoryaxis.setLabelFont(new Font(fontA, Font.PLAIN, 14));
		// 设置横坐标上显示各个子项的字体
		categoryaxis.setTickLabelFont(new Font(fontA, Font.PLAIN, 12));
		categoryaxis.setCategoryLabelPositionOffset(10);
		// categoryaxis.setMaximumCategoryLabelLines(100);
		// categoryaxis.setMaximumCategoryLabelWidthRatio(100);
		// 横坐标数据倾斜45度
		// categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		// 顶端设置
		numberaxis.setUpperMargin(0.14999999999999999D);
		// 设置颜色
		Paint apaint[] = createPaint();

		customBarRenderer
				.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());// 显示每个柱的数值
		customBarRenderer.setBaseItemLabelsVisible(true);
		// 注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题
		customBarRenderer
				.setBasePositiveItemLabelPosition(new ItemLabelPosition(
						ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		customBarRenderer.setItemLabelAnchorOffset(10D);// 设置柱形图上的文字偏离值

		// 设定柱子上面的颜色 
		customBarRenderer.setSeriesPaint(0,
		  Color.decode("#0084FF")); // 给series1 // Bar
		  customBarRenderer.setSeriesPaint(1, Color.decode("#7979FF")); 
		  // 给series2 // Bar 
		  customBarRenderer.setSeriesPaint(2,
		  Color.decode("#FF5555")); // 给series3 // Bar
		  customBarRenderer.setSeriesPaint(3, Color.decode("#F8D661"));
		  // 给series4 // Bar 
		  customBarRenderer.setSeriesPaint(4, Color.decode("#F284DC")); // 给series5 // Bar
		  customBarRenderer.setSeriesPaint(5, Color.decode("#00BF00")); 
		  // 给series6 // Bar 
		  customBarRenderer.setSeriesOutlinePaint(0,
		  Color.BLACK);// 边框为黑色 
		  customBarRenderer.setSeriesOutlinePaint(1,
		  Color.BLACK);// 边框为黑色 
		  customBarRenderer.setSeriesOutlinePaint(2,
		  Color.BLACK); // 边框为黑色 
		  customBarRenderer.setSeriesOutlinePaint(3,
		  Color.BLACK);// 边框为黑色 
		  customBarRenderer.setSeriesOutlinePaint(4,
		  Color.BLACK);// 边框为黑色 
		  customBarRenderer.setSeriesOutlinePaint(5,
		  Color.BLACK); // 边框为黑色 // 设置柱子的最大宽度
		  customBarRenderer.setMaximumBarWidth(0.04);
		  customBarRenderer.setItemMargin(0.000000005);
		 

		plot.setRenderer(customBarRenderer);
		return chart;
	}

	static class CustomBarRenderer3D extends BarRenderer3D {

		public Paint getItemPaint(int i, int j) {
			return colors[j % colors.length];
		}

		private Paint colors[];

		public CustomBarRenderer3D(Paint apaint[]) {
			colors = apaint;
		}

		public CustomBarRenderer3D() {
			// TODO Auto-generated constructor stub
		}
	}

	private static Paint[] createPaint() {
		Paint apaint[] = new Paint[5];
		apaint[0] = new GradientPaint(0.0F, 0.0F, Color.white, 0.0F, 0.0F,
				Color.decode("#66FF33"));
		apaint[1] = new GradientPaint(0.0F, 0.0F, Color.white, 0.0F, 0.0F,
				Color.decode("#66FF33"));
		apaint[2] = new GradientPaint(0.0F, 0.0F, Color.white, 0.0F, 0.0F,
				Color.decode("#66FF33"));
		apaint[3] = new GradientPaint(0.0F, 0.0F, Color.white, 0.0F, 0.0F,
				Color.decode("#66FF33"));
		apaint[4] = new GradientPaint(0.0F, 0.0F, Color.white, 0.0F, 0.0F,
				Color.decode("#66FF33"));
		return apaint;
	}

}
