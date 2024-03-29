package auxMaths;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
 
public class BarChart extends JPanel {
 
  protected double[] values;
  protected String[] labels;
  protected Color[] colors;
  protected String title;
 
  public BarChart(double[] values, String[] labels, Color[] colors, String title) {
    this.labels = labels;
    this.values = values;
    this.colors = colors;
    this.title = title;
  }
  
  public BarChart(String titre, double[] values) {
	  setValues(values);
	  title = titre;
  }
  
  
  protected BarChart(String titre) {
	  this(titre, new double[] {});
  }
  
  public void setValues(double[] values) {
	  this.values = values;
	  labels = new String[values.length];
	  colors = new Color[values.length];
	  for (int i =0; i<colors.length; i++) {
		  colors[i]=Color.red;
		  labels[i]="";
	  }
  }
  
  @Override
  public String getName() {
	  return title;
  }
 
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (values == null || values.length == 0) {
      return;
    }
 
    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < values.length; i++) {
      if (minValue > values[i]) {
        minValue = values[i];
      }
      if (maxValue < values[i]) {
        maxValue = values[i];
      }
    }
 
    Dimension dim = getSize();
    int panelWidth = dim.width;
    int panelHeight = dim.height;
    int barWidth = panelWidth / values.length;
 
    Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
 
    Font labelFont = new Font("Book Antiqua", Font.PLAIN, 14);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
 
    int titleWidth = titleFontMetrics.stringWidth(title);
    int stringHeight = titleFontMetrics.getAscent();
    int stringWidth = (panelWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, stringWidth, stringHeight);
 
    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue) {
      return;
    }
    double scale = (panelHeight - top - bottom) / (maxValue - minValue);
    stringHeight = panelHeight - labelFontMetrics.getDescent();
    g.setFont(labelFont);
    for (int j = 0; j < values.length; j++) {
      int valueP = j * barWidth + 1;
      int valueQ = top;
      int height = (int) (values[j] * scale);
      if (values[j] >= 0) {
        valueQ += (int) ((maxValue - values[j]) * scale);
      } else {
        valueQ += (int) (maxValue * scale);
        height = -height;
      }
 
      g.setColor(colors[j]);
      g.fillRect(valueP, valueQ, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueP, valueQ, barWidth - 2, height);
 
      int labelWidth = labelFontMetrics.stringWidth(labels[j]);
      stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
      g.drawString(labels[j], stringWidth, stringHeight);
    }
  }
 
  public static void main(String[] args) {
 
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Bar Chart Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(350, 300);
 
    String title = "My Title";
    double[] values = new double[]{1,2,3,4,5};
    String[] labels = new String[]{"A","B","C","D","E"};
    Color[] colors = new Color[]{
        Color.red,
        Color.orange,
        Color.yellow,
        Color.green,
        Color.blue
    };
    BarChart bc = new BarChart(values, labels,colors,title);
 
    frame.add(bc);
    frame.setVisible(true);
  }
}