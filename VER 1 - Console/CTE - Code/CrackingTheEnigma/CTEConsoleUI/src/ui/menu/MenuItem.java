package ui.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItem{
    private final String menuItemText;
    private final List<MenuItem> subItems = new ArrayList<>();


    public MenuItem(String text, MenuItem... args){
        this.menuItemText = text;
        subItems.addAll(Arrays.asList(args));
    }

    public void showMenu(){
        System.out.println(menuItemText);
        subItems.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return menuItemText;
    }
}
