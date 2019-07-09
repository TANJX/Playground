package basicExercise.random.sentence;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Main {

	final static String[] list = { "大江东去", "浪淘尽", "千古风流人物", "故垒西边", "人道是", "三国周郎赤壁", "乱石穿空", "惊涛拍岸", "卷起千堆雪", "江山如画",
			"一时多少豪杰", "遥想公瑾当年", "小乔初嫁了", "雄姿英发", "羽扇纶巾", "谈笑间", "强虏灰飞烟灭", "故国神游", "多情应笑我", "早生华发", "人生如梦", "一樽还酹江月",
			"莫听穿林打叶声", "何妨吟啸且徐行", "竹杖芒鞋轻胜马", "谁怕", "一蓑烟雨任平生", "料峭春风吹酒醒", "微冷", "山头斜照却相迎", "回首向来萧瑟处", "归去", "也无风雨也无晴",
			"楚天千里清秋", "水随天去秋无际", "遥岑远目", "献愁供恨", "玉簪螺髻", "落日楼头", "断鸿声里", "江南游子", "把吴钩看了", "栏干拍遍", "无人会", "登临意",
			"休说鲈鱼堪脍", "尽西风", "季鹰归未？求田问舍", "怕应羞见", "刘郎才气", "可惜流年", "忧愁风雨", "树犹如此", "倩何人唤取", "红巾翠袖", "英雄泪", "东风夜放花千树",
			"更吹落", "星如雨", "宝马雕车香满路", "凤箫声动", "玉壶光转", "一夜鱼龙舞", "蛾儿雪柳黄金缕", "笑语盈盈暗香去", "众里寻他千百度", "蓦然回首", "那人却在", "灯火阑珊处",
			"寻寻觅觅", "冷冷清清", "凄凄惨惨戚戚", "乍暖还寒时候", "最难将息", "三杯两盏淡酒", "怎敌他晚来风急", "雁过也", "正伤心", "却是旧时相识", "满地黄花堆积", "憔悴损",
			"如今有谁堪摘", "守着窗儿独自", "怎生得黑", "梧桐更兼细雨", "到黄昏点点滴滴", "这次第", "怎一个愁字了得", "薄雾浓云愁永昼", "瑞脑销金兽", "佳节又重阳", "玉枕纱厨",
			"半夜凉初透", "东篱把酒黄昏后", "有暗香盈袖", "莫道不销魂", "帘卷西风", "人比黄花瘦", "寒蝉凄切", "对长亭晚", "骤雨初歇", "都门帐饮无绪", "留恋处", "兰舟摧发",
			"执手相看泪眼", "竟无语凝噎", "念去去千里烟波", "暮霭沈沈楚天阔", "多情自古伤离别", "更那堪冷落清秋节", "今宵酒醒何处", "杨柳岸", "晓风残月", "此去经年", "应是良辰好景虚设",
			"便纵有千种风情", "更与何人说" };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("默写抽查");
		JButton button = new JButton("抽查");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Center on screen
		Toolkit toolkit = frame.getToolkit();
		Dimension size = toolkit.getScreenSize(); // resolution of the monitor
		frame.setLocation(size.width / 2 - frame.getWidth() / 2, size.height / 2 - frame.getHeight() / 2);

		frame.setSize(100, 100);
		frame.add(button);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = (int) (Math.random() * list.length);
				JOptionPane.showMessageDialog(frame,"请联想上下句: \n"+ list[index]);

			}
		});
		frame.setVisible(true);
	}

	public void oldMain() {
		System.out.println("Input sentences, end with \"-1\"");
		Scanner sc = new Scanner(System.in);
		ArrayList<String> list = new ArrayList<String>();
		String temp = sc.next();
		while (!temp.equals("-1")) {
			list.add(temp);
			temp = sc.next();
		}

		System.out.println("Input any integer, end output with \"-1\"");
		int op = sc.nextInt();
		while (op != -1) {
			int index = (int) (Math.random() * list.size());
			System.out.println(list.get(index));
			op = sc.nextInt();
		}
	}
}
