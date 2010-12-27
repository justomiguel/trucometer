/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jmv.settings;

/**
 *
 * @author justo.vargas
 */
public class Settings {

    // set the differents modes of ui
    private static final String FOSFOROS = "FOSFOROS";
    private static final String POROTOS = "POROTOS";

    // THE END OF THE GAME
    public int end_game;

    // Nombres de los jugadores
    public String usrTeam;
    public String rivalTeam;

    // UI configuration
    public String tantosUI;

    //
    private static Settings instance;

    public static Settings configuration(){
        if (instance == null) instance = new Settings();
        return instance;
    }

    private Settings() {
    }

    private Settings(int end_game, String usrTeam, String rivalTeam, String tantosUI) {
        this.end_game = end_game;
        this.usrTeam = usrTeam;
        this.rivalTeam = rivalTeam;
        this.tantosUI = tantosUI;
    }

    public int getEnd_game() {
        return end_game;
    }

    public void setEnd_game(int end_game) {
        this.end_game = end_game;
    }

    public String getRivalTeam() {
        return rivalTeam;
    }

    public void setRivalTeam(String rivalTeam) {
        this.rivalTeam = rivalTeam;
    }

    public String getTantosUI() {
        return tantosUI;
    }

    public void setTantosUI(String tantosUI) {
        this.tantosUI = tantosUI;
    }

    public String getUsrTeam() {
        return usrTeam;
    }

    public void setUsrTeam(String usrTeam) {
        this.usrTeam = usrTeam;
    }



}

