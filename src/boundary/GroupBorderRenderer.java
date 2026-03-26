package boundary;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class GroupBorderRenderer extends DefaultTableCellRenderer {
    
    private int statusColumnIndex; 
    private boolean drawThickLine;

    public GroupBorderRenderer(int statusColumnIndex, boolean drawThickLine) {
        this.statusColumnIndex = statusColumnIndex;
        this.drawThickLine = drawThickLine;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Object facilityValue = table.getModel().getValueAt(row, 0);
        boolean isNewFacility = (facilityValue != null && !facilityValue.toString().trim().isEmpty());

        if (drawThickLine && isNewFacility && row > 0) {
            javax.swing.border.Border thickLine = javax.swing.BorderFactory.createMatteBorder(2, 0, 0, 0, java.awt.Color.BLACK);
            javax.swing.border.Border padding = javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4); 
            ((javax.swing.JComponent) cell).setBorder(javax.swing.BorderFactory.createCompoundBorder(thickLine, padding));
        } else {
            ((javax.swing.JComponent) cell).setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 4, 2, 4));
        }

        String status = "";
        Object statusObj = table.getModel().getValueAt(row, statusColumnIndex);
        if (statusObj != null) {
            status = statusObj.toString().trim();
        }

        if (status.equals("Expired")) {
            cell.setBackground(new java.awt.Color(230, 230, 230));
            cell.setForeground(java.awt.Color.GRAY);               
        } 
        else {
            cell.setForeground(table.getForeground());

            if (!isSelected) {
                if (column == statusColumnIndex) {
                    switch (status) {
                        case "Available": 
                            cell.setBackground(new java.awt.Color(144, 238, 144)); 
                            break;
                        case "Booked": 
                        case "Unavailable": // Added Unavailable to turn pink too
                            cell.setBackground(new java.awt.Color(255, 182, 193)); 
                            break;
                        case "Maintenance": 
                            cell.setBackground(new java.awt.Color(255, 250, 205)); 
                            break;
                        default: 
                            cell.setBackground(java.awt.Color.WHITE); 
                            break;
                    }
                } else {
                    cell.setBackground(java.awt.Color.WHITE); 
                }
            }
        }
        
        ((javax.swing.JLabel) cell).setOpaque(true);
        return cell;
    }
}