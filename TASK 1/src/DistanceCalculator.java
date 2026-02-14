package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceCalculator extends JFrame {

    private JTextField txtLat1, txtLon1, txtLat2, txtLon2;
    private JTextField txtResult;
    private JButton btnSolve, btnClear;
    
    public DistanceCalculator() {
        // Window setup
        setTitle("Distance Calculator (Haversine Formula)");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setLayout(new GridLayout(6, 2, 10, 10)); // Grid: 6 rows, 2 columns
        Font myFont = new Font("Arial", Font.BOLD, 14);
        // Create components
        // Point 1
        add(new JLabel("Lat 1 (Latitude, deg):", SwingConstants.RIGHT));
        txtLat1 = new JTextField();
        add(txtLat1);

        add(new JLabel("Lon 1 (Longitude, deg):", SwingConstants.RIGHT));
        txtLon1 = new JTextField();
        add(txtLon1);

        // Point 2
        add(new JLabel("Lat 2 (Latitude, deg):", SwingConstants.RIGHT));
        txtLat2 = new JTextField();
        add(txtLat2);

        add(new JLabel("Lon 2 (Longitude, deg):", SwingConstants.RIGHT));
        txtLon2 = new JTextField();
        add(txtLon2);

        // Buttons
        btnSolve = new JButton("Solve");
        btnClear = new JButton("Clear");
        add(btnSolve);
        add(btnClear);

        // Result field
        add(new JLabel("Distance (m):", SwingConstants.RIGHT));
        txtResult = new JTextField();
        txtResult.setEditable(false); // Disable editing for result
        add(txtResult);

        // change button colors
        btnSolve.setBackground(Color.GREEN);
        btnSolve.setForeground(Color.BLACK);
        btnSolve.setFont(myFont);
        txtResult.setFont(myFont);
        // change background color
        getContentPane().setBackground(Color.LIGHT_GRAY);
        // Add logic (Event Listeners)
        btnSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateDistance();
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }

    // Method to clear fields
    private void clearFields() {
        txtLat1.setText("");
        txtLon1.setText("");
        txtLat2.setText("");
        txtLon2.setText("");
        txtResult.setText("");
    }

    // Calculation logic using formulas from the document
    private void calculateDistance() {
        try {
            // Read coordinates (degrees)
            double lat1 = Double.parseDouble(txtLat1.getText());
            double lon1 = Double.parseDouble(txtLon1.getText());
            double lat2 = Double.parseDouble(txtLat2.getText());
            double lon2 = Double.parseDouble(txtLon2.getText());

       
            final double R = 6371e3;

     
            double phi1 = Math.toRadians(lat1);
            double phi2 = Math.toRadians(lat2);

            double deltaPhi = Math.toRadians(lat2 - lat1);
            double deltaLambda = Math.toRadians(lon2 - lon1);

            // a = sin^2(Δφ/2) + cos(φ1) * cos(φ2) * sin^2(Δλ/2)
            double a = Math.pow(Math.sin(deltaPhi / 2), 2) +
                       Math.cos(phi1) * Math.cos(phi2) *
                       Math.pow(Math.sin(deltaLambda / 2), 2);

       
            // c = 2 * atan2(sqrt(a), sqrt(1-a))
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

  
            double distance = R * c;

            // Output result (formatted to 2 decimal places)
            txtResult.setText(String.format("%.2f m", distance));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Launch GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DistanceCalculator().setVisible(true);
            }
        });
    }
}