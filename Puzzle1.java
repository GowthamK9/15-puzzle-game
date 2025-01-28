package com.javapractise.looping;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Puzzle1 extends JFrame{
    JButton[][] sq = new JButton[4][4];
    int m,n;

    public Puzzle1()
    {
        Container container = getContentPane();
        container.setLayout(new GridLayout(4,4));

        ButaneListener pushed = new ButaneListener();

        for(int i=0;i<4;i++)
        {
            for(int j=0;j<4;j++)
            {
                JButton n = new JButton();
                sq[i][j] = n;
                sq[i][j].addActionListener(pushed);
                sq[i][j].setBackground(Color.pink);
                container.add(sq[i][j]);
            }
        }
    }

    public static void main(String args[])
    {
        Puzzle1 game = new Puzzle1();
        game.setTitle("THE FIFTEEN PUZZLE");
        game.setVisible(true);
        game.setSize(400,400);
        game.scramble();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void scramble()
    {
        boolean[] used = new boolean[16];
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<4; j++)
            {
                int val = (int)(16*Math.random());
                while(used[val])
                {
                    val = (int)(16*Math.random());
                }
                used[val] = true;
                if(val!=0)
                    sq[i][j].setText(""+val);
                else
                {
                    sq[i][j].setBackground(Color.gray);
                    m=i; n=j;
                }
            }
        }
    }


    class ButaneListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Object square = e.getSource();
            outer: for(int i=0; i<4; i++)
            {
                for(int j=0; j<4; j++)
                {
                    if(sq[i][j]== square)
                    {
                        moves(i,j);
                        break outer;
                    }
                }
            }
        }
    }

    public void moves(int i, int j)
    {
        if((i+1==m && j==n) || (i-1==m && j==n) || (i==m && j+1==n) || (i==m && j-1==n))
        {
            sq[m][n].setText(sq[i][j].getText());
            sq[m][n].setBackground(Color.pink);
            sq[i][j].setText("");
            sq[i][j].setBackground(Color.gray);
            m=i; n=j;
        }
    }
}