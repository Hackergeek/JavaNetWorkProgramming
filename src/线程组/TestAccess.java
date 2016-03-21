/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ïß³Ì×é;

/**
 *
 * @author geek
 */
public class TestAccess {
    public static void main(String[] args) {
        ThreadGroup
                x = new ThreadGroup("x"),
                y = new ThreadGroup(x, "y"),
                z = new ThreadGroup(y, "z");
    }
}
