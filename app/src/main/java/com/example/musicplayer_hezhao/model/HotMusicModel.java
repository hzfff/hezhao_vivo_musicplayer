package com.example.musicplayer_hezhao.model;

/**
 * Created by 11120555 on 2020/7/8 17:04
 */
public class HotMusicModel {
    private String Id;
    private String MusicName;
    private String  HotNumber;
    private String Instruction;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMusicName() {
        return MusicName;
    }

    public void setMusicName(String musicName) {
        MusicName = musicName;
    }

    public String getHotNumber() {
        return HotNumber;
    }

    public void setHotNumber(String hotNumber) {
        HotNumber = hotNumber;
    }

    public String getInstruction() {
        return Instruction;
    }

    public void setInstruction(String instruction) {
        Instruction = instruction;
    }

    public HotMusicModel(String id, String musicName, String hotNumber, String instruction) {
        Id = id;
        MusicName = musicName;
        HotNumber = hotNumber;
        Instruction = instruction;
    }
}
