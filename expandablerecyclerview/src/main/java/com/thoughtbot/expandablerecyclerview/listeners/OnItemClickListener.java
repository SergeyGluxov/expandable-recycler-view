package com.thoughtbot.expandablerecyclerview.listeners;

public interface OnItemClickListener {
    boolean onBeforeItemClick(int flatPos, boolean b);
    boolean onItemClick(int flatPos, boolean b);
}
