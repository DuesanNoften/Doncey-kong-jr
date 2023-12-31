package ServerApp;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.awt.BorderLayout;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import Game.*;
/**
* Clase Singleton para controlar y crear la interfaz del servidor
*/
public class App extends Canvas implements ActionListener {

    // JTextField
    private static App singleApp;

    private static Font f;
    static ImageIcon icon;
    static String gameObj[] = {"enemigo azul", "enemigo rojo", "fruta"};
    static String gameOpt[] = {"Crear", "Eliminar"};
    static Integer gameLiana[] = {1,2,3,4,5,6,7,8,9,10};
    static JTextField textJ1;
    static JTextField textJ2;
    static JFrame frame;
    static JButton buttonJ1;
    static JButton buttonJ2;
    static JButton addLife1;
    static JButton addLife2;
    static JButton removeLife1;
    static JButton removeLife2;
    static JButton addPoints1;
    static JButton addPoints2;
    static JButton removePoints1;
    static JButton removePoints2;
    static JSlider sliderJ1;
    static JSlider sliderJ2;
    static JSpinner objJ1, optJ1, liaJ1;
    static JSpinner objJ2, optJ2, liaJ2;
    static JLabel labelJ1;
    static JLabel labelJ2;
    static Juego juego1;
    static Juego juego2;

    static ServerHandler server1;
    static ServerHandler server2;
    /**
     * Clase constructora de App
     */
    App() {
        juego1 = new Juego();
        juego2 = new Juego();
        f = new Font("Impact", Font.PLAIN, 16);
    }
    /**
     * Clase para obtener la instancia de App
     * @return La instancia unica que se tiene de la aplicación
     */
    public static App getInstance() {
        if(singleApp == null) {
            singleApp = new App();
        }
        return singleApp;
    }

    /**
     * main utilizado para la ejecucion del programa
     * @param args parametro necesario para el main
     * @throws MalformedURLException Por si se llegase a generar un error al formar la URL
     * @throws IOException por si se ingresase o obtuviese un valor erroneo
     */
    public static void main(String[] args) throws MalformedURLException, IOException {

        App app = App.getInstance();
        ImageIcon icon = new ImageIcon("src/img/ico.png");
        frame = new JFrame("DonCEy Kong Jr. Server");
        frame.setIconImage(icon.getImage());
        JLabel label = new JLabel("en liana");
        label.setFont(f);
        label.setForeground(new Color(179, 207, 221));

        JLabel label2 = new JLabel("en liana");
        label2.setFont(f);
        label2.setForeground(new Color(179, 207, 221));

        buttonJ1 = new JButton("Enviar a J1");
        buttonJ1.setBackground(new Color(179, 207, 221));
        buttonJ1.setForeground(Color.BLACK);

        addLife1=new JButton("1UP J1");
        addLife1.setBackground(new Color(179, 207, 221));
        addLife1.setForeground(Color.BLACK);

        removeLife1=new JButton("-1UP J1");
        removeLife1.setBackground(new Color(179, 207, 221));
        removeLife1.setForeground(Color.BLACK);

        addPoints1 = new JButton("+100 pts J1");
        addPoints1.setBackground(new Color(179, 207, 221));
        addPoints1.setForeground(Color.BLACK);

        removePoints1 = new JButton("-100 pts J1");
        removePoints1.setBackground(new Color(179, 207, 221));
        removePoints1.setForeground(Color.BLACK);

        buttonJ2 = new JButton("Enviar a J2");
        buttonJ2.setBackground(new Color(179,207,221));
        buttonJ2.setForeground(Color.BLACK);

        addLife2=new JButton("1UP J2");
        addLife2.setBackground(new Color(179, 207, 221));
        addLife2.setForeground(Color.BLACK);

        removeLife2=new JButton("-1UP J2");
        removeLife2.setBackground(new Color(179, 207, 221));
        removeLife2.setForeground(Color.BLACK);

        addPoints2 = new JButton("+100 pts J2");
        addPoints2.setBackground(new Color(179, 207, 221));
        addPoints2.setForeground(Color.BLACK);

        removePoints2 = new JButton("-100 pts J2");
        removePoints2.setBackground(new Color(179, 207, 221));
        removePoints2.setForeground(Color.BLACK);

        buttonJ1.addActionListener(app);
        addLife1.addActionListener(app);
        removeLife1.addActionListener(app);
        addPoints1.addActionListener(app);
        removePoints1.addActionListener(app);
        buttonJ2.addActionListener(app);
        addLife2.addActionListener(app);
        removeLife2.addActionListener(app);
        addPoints2.addActionListener(app);
        removePoints2.addActionListener(app);

        sliderJ1 = new JSlider(JSlider.VERTICAL);
        sliderJ1.setBackground(Color.BLACK);
        sliderJ1.setForeground(new Color(179,207,221));
        sliderJ1.setMinorTickSpacing(10);
        sliderJ1.setPaintLabels(true);
        sliderJ1.setSnapToTicks(true);
        sliderJ1.setInverted(true);
        sliderJ1.setLabelTable(sliderJ1.createStandardLabels(10));
        sliderJ1.setFont(f);

        sliderJ2 = new JSlider(JSlider.VERTICAL);
        sliderJ2.setBackground(Color.BLACK);
        sliderJ2.setForeground(new Color(179,207,221));
        sliderJ2.setMinorTickSpacing(10);
        sliderJ2.setPaintLabels(true);
        sliderJ2.setSnapToTicks(true);
        sliderJ2.setInverted(true);
        sliderJ2.setLabelTable(sliderJ2.createStandardLabels(10));
        sliderJ2.setFont(f);

        objJ1 = new JSpinner(new SpinnerListModel (gameObj));
        setSpinnerProperties(objJ1, 8);

        optJ1 = new JSpinner(new SpinnerListModel (gameOpt));
        setSpinnerProperties(optJ1, 5);

        liaJ1 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        setSpinnerProperties(liaJ1 , 3);

        objJ2 = new JSpinner(new SpinnerListModel (gameObj));
        setSpinnerProperties(objJ2, 8);

        optJ2 = new JSpinner(new SpinnerListModel (gameOpt));
        setSpinnerProperties(optJ2, 5);

        liaJ2 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        setSpinnerProperties(liaJ2, 3);

        //JPanel panelJ1 = new JPanel();
        ImagePanel panelJ1 = new ImagePanel(new ImageIcon("src/img/fondo.jpg").getImage());
        panelJ1.setPreferredSize(new Dimension(1000, 310));
        panelJ1.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelJ1.add(optJ1);
        panelJ1.add(objJ1);
        panelJ1.add(label);
        panelJ1.add(liaJ1);
        panelJ1.add(sliderJ1);
        panelJ1.add(buttonJ1);
        panelJ1.add(addLife1);
        panelJ1.add(removeLife1);
        panelJ1.add(addPoints1);
        panelJ1.add(removePoints1);

        ImagePanel panelJ2 = new ImagePanel(new ImageIcon("src/img/fondo2.jpg").getImage());
        panelJ2.setPreferredSize(new Dimension(1000, 300));
        panelJ2.setBorder(BorderFactory.createEmptyBorder(40,40,40,40));
        panelJ2.add(optJ2);
        panelJ2.add(objJ2);
        panelJ2.add(label2);
        panelJ2.add(liaJ2);
        panelJ2.add(sliderJ2);
        panelJ2.add(buttonJ2);
        panelJ2.add(addLife2);
        panelJ2.add(removeLife2);
        panelJ2.add(addPoints2);
        panelJ2.add(removePoints2);

        frame.add(panelJ1, BorderLayout.NORTH);
        frame.add(panelJ2, BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(1400, 650));
        frame.pack();
        frame.setVisible(true);

        server1 = new ServerHandler(1108,juego1);
        server2 = new ServerHandler(802,juego2);

        server1.start();
        server2.start();

        //Revisar si se cierra la ventana, si ocurriese matar el programa
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    /**
     * Funcion para configurar las propiedades de un spinner
     * @param spinner Objeto de la clase spinner de JFrame
     * @param width ancho del spinner
     */
    public static void setSpinnerProperties(JSpinner spinner, Integer width) {
        spinner.setFont(f);
        ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setColumns(width);
        ((JSpinner.DefaultEditor)spinner.getEditor()).getTextField().setEditable(false);;
        spinner.getEditor().getComponent(0).setBackground(Color.BLACK);
        spinner.getEditor().getComponent(0).setForeground(new Color(179,207,221));
    }

    /**
     * Evento al presionar los botones de la interfaz, toma todos los datos de los componentes y realiza la acción respectiva
     * @param e evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Integer x_liana = 0;
        Integer y_percentage = 0;
        String opcion = "";
        String objeto = "";
        String s = e.getActionCommand();
        Juego juego = new Juego();
        if (s.equals("1UP J1")){
            juego1.jugador.addlife();
        }
        if (s.equals("1UP J2")){
            juego2.jugador.addlife();
        }
        if (s.equals("-1UP J1")){
            juego1.jugador.takelife();
        }
        if (s.equals("-1UP J2")){
            juego2.jugador.takelife();
        }
        if (s.equals("+100 pts J1")){
            juego1.addPoints();
            System.out.println("Se añadieron 100 puntos al J1");
        }
        if (s.equals("+100 pts J2")){
            juego2.addPoints();
            System.out.println("Se añadieron 100 puntos al J2");
        }
        if (s.equals("-100 pts J1")){
            if (juego1.getPoints()>=100) {
                juego1.removePoints();
                System.out.println("Se le quitaron 100 puntos al J1");
            }
            else {

            }
        }
        if (s.equals("-100 pts J2")){
            if (juego2.getPoints()>=100) {
                juego2.removePoints();
                System.out.println("Se le quito 100 puntos al J2");
            }
            else {

            }
        }
        if (s.equals("Enviar a J1")) {
            x_liana = (Integer)liaJ1.getValue();
            y_percentage = sliderJ1.getValue();
            opcion = (String)optJ1.getValue();
            objeto = (String)objJ1.getValue();
            juego = juego1;
        }
        if (s.equals("Enviar a J2")) {
            x_liana = (Integer)liaJ2.getValue();
            y_percentage = sliderJ2.getValue();
            opcion = (String)optJ2.getValue();
            objeto = (String)objJ2.getValue();
            juego = juego2;
        }
        if (opcion.equals("Crear")) {
            if(objeto.equals("enemigo rojo")) {
                System.out.println("creando enemigo rojo");
                juego.crear_cocodrilo("1", x_liana, y_percentage);
            }
            if(objeto.equals("enemigo azul")) {
                System.out.println("creando enemigo azul");
                juego.crear_cocodrilo("2", x_liana, y_percentage);

            }
            if(objeto.equals("fruta")) {
                System.out.println("creando fruta");
                juego.crear_fruta(x_liana, y_percentage);

            }
        }
        if (opcion.equals("Eliminar")) {
            if(objeto.equals("enemigo rojo")) {
                System.out.println("eliminando enemigo rojo");
                juego.eliminar_cocodrilo("1", x_liana);

            }
            if(objeto.equals("enemigo azul")) {
                System.out.println("eliminando enemigo azul");
                juego.eliminar_cocodrilo("2", x_liana);
            }
            if(objeto.equals("fruta")) {
                juego.eliminar_fruta(x_liana, y_percentage);
            }
        }

        System.out.println("juego1: ");
        System.out.println(juego1.game_str());
        System.out.println("juego2: ");
        System.out.println(juego2.game_str());

    }
}

class ImagePanel extends JPanel {
    private static final Long serialVersionUID = 1L;
    private Image image = null;
    private Integer iWidth2;
    private Integer iHeight2;

    /**
     * Funcion que busca una imagen y toma su tamaño como parametro
     * @param image Imagen que sera utilizada para el fondo
     */
    public ImagePanel(Image image) {
        this.image = image;
        this.iWidth2 = image.getWidth(this)/2;
        this.iHeight2 = image.getHeight(this)/2;
    }

    /**
     * Funcion usada para colocar la imagen de fondo
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, null);
        }
    }
}
