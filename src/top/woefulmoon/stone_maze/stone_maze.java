package top.woefulmoon.stone_maze;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class stone_maze extends JFrame {
    //目标：制作一个石头迷阵的游戏
    //1、创建一个二维数组，存储石头迷阵的元素。(4 * 4)
    private int[][] maze = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    private int x = 3,y = 3;
    private int[][] WINmaze = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    private int count = 0;

    public stone_maze() {
        this.initFrame();
        this.initMenu();
        this.newInitmaze();
        this.initImage();
        //绑定窗口事件，监听键盘上下左右键
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //获取键盘按下的键值
                int keyCode = e.getKeyCode();
                System.out.println(count);
                //判断按下的键值
                if (keyCode == KeyEvent.VK_UP) {
                    moveUp();
                }
                else if (keyCode == KeyEvent.VK_DOWN) {
                    moveDown();
                }
                else if (keyCode == KeyEvent.VK_LEFT) {
                    moveLeft();
                }
                else if (keyCode == KeyEvent.VK_RIGHT) {
                    moveRight();
                }
            }

        });
        this.setVisible(true);
    }
    //判断迷阵是否完成：即maze数组与WINmaze数组是否相同
    private boolean isWin(){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] != WINmaze[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    private void moveLeft() {
        if(y != 3){
            maze[x][y] = maze[x][y+1];
            maze[x][y+1] = 0;
            y++;
            count++;
        }
        initImage();
    }

    private void moveRight() {
        if(y != 0){
            maze[x][y] = maze[x][y-1];
            maze[x][y-1] = 0;
            y--;
            count++;
        }
        initImage();
    }

    private void moveUp() {
        if(x != 3){
            maze[x][y] = maze[x+1][y];
            maze[x+1][y] = 0;
            x++;
            count++;
        }
        initImage();

    }

    private void moveDown() {
        if(x != 0){
            maze[x][y] = maze[x-1][y];
            maze[x-1][y] = 0;
            x--;
            count++;
        }
        initImage();

    }

    //图片初始化
    private void initImage() {
        //先清除窗口中的图片
        this.getContentPane().removeAll();
        if (isWin()){
            JLabel Winlabel = new JLabel();
            Winlabel.setIcon(new ImageIcon("demo2/src/image/win.png"));
            Winlabel.setBounds(100, 200, 270, 90);
            this.add(Winlabel);
        }
        //遍历二维数组，获取每个元素
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                //获取每个元素
                String imageName = maze[i][j] + ".png";
                //创建标签
                JLabel label = new JLabel();
                label.setIcon(new ImageIcon("demo2/src/image/" + imageName));
                label.setBounds(100 * j + 20, 100 * i + 105, 100, 100);
                this.add(label);
            }
        }
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("demo2/src/image/background.png"));
        label.setBounds(0, 0, 465, 540);
        this.add(label);
        //添加一个label用于展示步数count
        JLabel labelText = new JLabel("步数：" + count);
        labelText.setBounds(0, 0, 100, 30);
        this.add(labelText);
        //刷新图层
        this.repaint();
    }

    private void initMenu() {
        //初始化菜单栏
        //1、创建菜单栏
        //2、菜单栏中有重启和关闭 两个选项
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("游戏");
        JMenuItem restart = new JMenuItem("重新开始");
        restart.addActionListener(e -> {
            this.newInitmaze();
            this.initImage();

        });
        JMenuItem close = new JMenuItem("关闭");
        close.addActionListener(e -> {
            dispose();
        });
        menu.add(restart);
        menu.add(close);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    //窗口初始化
    private void initFrame() {
        this.setTitle("石头迷阵");
        this.setSize(465, 575);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    private void initMaze() {
        //打乱数组，初始化迷阵
        //实现：随机交换数组中的元素
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                //获取随机索引
                int X1index = (int) (Math.random() * maze.length);
                int Y1index = (int) (Math.random() * maze.length);
                int X2index = (int) (Math.random() * maze.length);
                int Y2index = (int) (Math.random() * maze.length);
                //交换元素
                int temp = maze[X1index][Y1index];
                maze[X1index][Y1index] = maze[X2index][Y2index];
                maze[X2index][Y2index] = temp;
            }
        }
        //获取0元素的索引
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

    }
    private void newInitmaze() {
        for (int i = 0; i < 1000; i++){
            int select = (int) (Math.random() * 4);
            switch (select) {
                case 0:
                    moveDown();
                    break;
                case 1:
                    moveUp();
                    break;
                case 2:
                    moveLeft();
                    break;
                case 3:
                    moveRight();
                    break;
            }
        }
        count = 0;
    }


}
