package pt.brunojesus.simpleui.menu;


import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JFrame {

    private final MenuParameters menuParameters;
    private DefaultListModel<MenuItem> menuItems;

    public Menu(MenuParameters menuParameters) {
        this.menuParameters = menuParameters;

        initMenuItems();
        final JList<MenuItem> menuItemJList = initJList();
        initJListActions(menuItemJList);
        initUi();
    }

    private void initMenuItems() {
        this.menuItems = new DefaultListModel<>();
        this.menuParameters.getMenuItems().forEach(this.menuItems::addElement);
    }

    private JList<MenuItem> initJList() {
        JList<MenuItem> menuItemJList = new JList<>(this.menuItems);
        add(new JScrollPane(menuItemJList));

        if (this.menuParameters.getFontSize() != null) {
            menuItemJList.setFont(new Font("Arial", Font.BOLD, this.menuParameters.getFontSize()));
        }

        return menuItemJList;
    }

    private void initJListActions(JList<MenuItem> menuItemJList) {
        menuItemJList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                menuItemJList.getSelectedValue().getSelectionAction().run();
            }
        });

        menuItemJList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    int index = menuItemJList.locationToIndex(evt.getPoint());
                    if (index > -1) {
                        MenuItem menuItem = menuItemJList.getModel().getElementAt(index);
                        if (menuItem.getRightClickAction() != null) {
                            menuItem.getRightClickAction().run();
                        }
                    }
                } else if (evt.getClickCount() == 1 && menuItemJList.getSelectedValue().getSingleClickAction() != null) {
                    menuItemJList.getSelectedValue().getSingleClickAction().run();
                } else if (evt.getClickCount() == 2 && menuItemJList.getSelectedValue().getDoubleClickAction() != null) {
                    menuItemJList.getSelectedValue().getDoubleClickAction().run();
                }
            }
        });
    }

    public void initUi() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (this.menuParameters.getFontSize() != null) {
            this.setFont(new Font("Arial", Font.BOLD, this.menuParameters.getFontSize()));
        }

        if (StringUtils.isNotBlank(menuParameters.getTitle())) {
            this.setTitle(menuParameters.getTitle());
        }

        if (menuParameters.getDimension() != null) {
            this.setSize(menuParameters.getDimension());
        } else {
            this.setSize(new Dimension(200, 200));
        }

        this.setLocationRelativeTo(menuParameters.getParent());
    }

}
