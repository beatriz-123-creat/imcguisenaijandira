package view;

import model.Imc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaImc {

    private JPanel painelTitulo = new JPanel();
    private JLabel labelTitulo = new JLabel("Índice de Massa Corporal - IMC");

    private JLabel lblPeso = new JLabel("Peso: ");
    private JTextField txtPeso = new JTextField();

    private JLabel lblAltura = new JLabel("Altura");
    private JTextField txtAltura = new JTextField();

    private JLabel lblTituloResultadoImc = new JLabel("Resultado do IMC:");
    private  JTextField txtResultadoImc = new JTextField("");

    private JLabel lblStatusImc = new JLabel("Normal");

    private JButton btnBotaoCalcular = new JButton("CALCULAR");
    private JButton btnBotaoLimpar = new JButton("LIMPAR");

    private String imagePath = "../images/";
    private Icon iconBtnCalcular = new ImageIcon(getClass().getResource(imagePath + "calc2.png"));
    private Icon iconBtnLimpar = new ImageIcon(getClass().getResource(imagePath + "restart24.png"));


    int peso = 0;
    double altura = 0.0;


    public TelaImc(){
        criarTela();
    }

    public void criarTela (){
        //DEFINIR TELA
        JFrame tela = new JFrame();
        tela.setTitle("Calculadora de IMC");
        tela.setSize(500, 300);
        tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        tela.setLayout(null);


        painelTitulo.setBounds(0,0,500, 50);
        painelTitulo.setBackground(new Color(80, 5, 39));
        painelTitulo.setLayout(null);

        labelTitulo.setBounds(10, 10, 200, 30);
        painelTitulo.add(labelTitulo);

        // COMPONENTE:  Tela/PESO//
        lblPeso.setBounds(10, 70, 150, 30);
        txtPeso.setBounds(10, 100, 150, 35);
        txtPeso.setFont(new Font("Arial", Font.BOLD, 24));

        //COMPONENTE:  TELA/ALTURA//
        lblAltura.setBounds(10, 140, 150, 30);
        txtAltura.setBounds(10, 170, 150, 30);
        txtAltura.setFont(new Font("Arial", Font.BOLD, 24));


        //COMPONENTE:  FONTE//
        lblTituloResultadoImc.setBounds(225,70,250, 30);
        lblTituloResultadoImc.setForeground(Color.pink);
        lblTituloResultadoImc.setFont(new Font("Arial", Font.BOLD, 38));
        lblTituloResultadoImc.setHorizontalAlignment(JLabel.CENTER);

        txtResultadoImc.setBounds(225, 120,250, 30);
        txtResultadoImc.setFont(new Font("Arial", Font.BOLD, 38));
        txtResultadoImc.setForeground(Color.red);
        txtResultadoImc.setHorizontalAlignment(JLabel.CENTER);

        lblStatusImc.setBounds(225,  150,250,30);
        lblStatusImc.setFont(new Font("Arial", Font.BOLD, 24));
        lblStatusImc.setForeground(Color.blue);
        lblStatusImc.setHorizontalAlignment(JLabel.CENTER);


        //COMPONENTE BOTAO//
        btnBotaoCalcular.setBounds(10, 220, 130, 20);
        btnBotaoCalcular.setIcon(iconBtnCalcular);

        btnBotaoCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularImc();
            }
        });

        btnBotaoLimpar.setBounds(10, 250, 130, 20);
        btnBotaoLimpar.setIcon(iconBtnLimpar);

        btnBotaoLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparTela();
            }
        });


        //MOSTRAR NA TELA//
        tela.getContentPane().add(painelTitulo);

        tela.getContentPane().add((lblPeso));
        tela.getContentPane().add((txtPeso));

        tela.getContentPane().add((lblAltura));
        tela.getContentPane().add((txtAltura));

        tela.getContentPane().add(lblTituloResultadoImc);
        tela.getContentPane().add(txtResultadoImc);

        tela.getContentPane().add(lblStatusImc);
        tela.getContentPane().add(btnBotaoCalcular);
        tela.getContentPane().add(btnBotaoLimpar);


        tela.setVisible(true);
    }

    private void limparTela(){

        txtAltura.setText("");
        txtPeso.setText("");
        lblStatusImc.setText("");
        lblTituloResultadoImc.setText("");
        txtPeso.requestFocus();

    }

    private void calcularImc(){

        if(validarDados()){

            Imc imc = new Imc();

            imc.setPeso(peso);
            imc.setAltura(altura);

            String resultImc = String.valueOf(imc.getImc());

            if(imc.getImc() <= 18.5 || imc.getImc() <= 25.0){
                painelTitulo.setBackground(Color.GREEN);
                lblTituloResultadoImc.setForeground(Color.GREEN);
            } else{
                painelTitulo.setBackground(Color.red);
                lblTituloResultadoImc.setForeground(Color.RED);
            }

            lblTituloResultadoImc.setText(resultImc);
                String.format("%,2f", imc.getImc()).replace(".", ",");
            lblStatusImc.setText(imc.getStatus());
        }

    }

    private boolean validarDados(){
        try{
            peso = Integer.parseInt(txtPeso.getText().trim());
        }catch (NumberFormatException erro){
            System.out.println(erro);
            JOptionPane.showMessageDialog(
                   null,
            "A altura deve ser um valor numérico",
            "Valor Inváido",
            JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        try{
            altura = Double.parseDouble(txtAltura.getText().replace(",", ".").trim());

        }catch(NumberFormatException erro){
            JOptionPane.showMessageDialog(
                    null,
                    "A altura deve ser um valor numérico",
                    "Valor Inváido",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
        return true;
    }

}
