package cronometroaps;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class telateste extends WindowAdapter implements ActionListener, FocusListener, KeyListener {
    public static TextField tsegundo, tminuto, tmilessimo;
    public static Label lsegundo, lminuto, lmilessimo;
    private JTable tabelacon;
    private DefaultTableModel tabelamold;
    private JFrame janela;
    private JPanel painel, painelbot, painelcon;
    private Label lnome_vei, lnome_equi, lnome_pil, lvolta1, lvolta2, lvoltatot;
    private TextField tnome_vei;
    private TextField tnome_equi;
    private TextField tnome_pil;
    private TextField tvolta1;
    private TextField tvolta2;
    private TextField tvoltatot;
    private TextField tcodigo_eqi;
    private Button bSalvar, bPausar, bRedefinir, bIniciar, bConsultar;
    private MenuBar menuprin;
    private Menu m1;
    private MenuItem m11, m12, m13;
    private Vector vTela;
    private int posicao;
    Thread tmin, tseg, tmili;
    minuto minuto;
    segundo segundo;
    milessimo milessimo;
    int clicar = 0;

    public telateste() {
        vTela = new Vector();
        janela = new JFrame();
        janela.setSize(1000, 650);
        janela.setTitle("Cronômetro");
        janela.setBackground(new Color(160, 160, 160));
        janela.addWindowListener(this);
        janela.setLayout(null);
        
        //criando a tabela
        tabelamold = new DefaultTableModel();
        tabelamold.addColumn("ID");
        tabelamold.addColumn("Veículo");
        tabelamold.addColumn("Equipe");
        tabelamold.addColumn("Piloto");
        tabelamold.addColumn("1ºVolta");
        tabelamold.addColumn("2ºVolta");
        tabelamold.addColumn("Total");
        tabelacon = new JTable(tabelamold);
        //adicionando a tabela a um ScrollPane
        JScrollPane scrollPane = new JScrollPane(tabelacon);
        
        
        
        //criando o painel
        painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(Color.GRAY);
        // craindo painel dos botões
        painelbot = new JPanel();
        painelbot.setLayout(null);
        painelbot.setBackground(Color.darkGray);
        // criando painel botao consultar
        painelcon = new JPanel();
        painelcon.setLayout(new BorderLayout());
        painelcon.setBackground(Color.GRAY);
        painelcon.add(scrollPane, BorderLayout.CENTER);
        // criando menu para a tela
        menuprin = new MenuBar();
        m1 = new Menu("Cronômetro");
        m11 = new MenuItem("Cadastro");
        m12 = new MenuItem("Visualizar Resgistro");
        m13 = new MenuItem("Sair");
        m1.add(m11);
        m1.addSeparator();
        m1.add(m12);
        m1.addSeparator();
        m1.add(m13);
        menuprin.add(m1);
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        // criar rótulos para o painel
        lnome_vei = new Label("Nome Veículo");
        lnome_equi = new Label("Nome Equipe");
        lnome_pil = new Label("Nome Piloto");
        lvolta1 = new Label("1º Volta");
        lvolta2 = new Label("2º Volta");
        lvoltatot = new Label("Tempo total Volta");
        lsegundo = new Label("Segundos");
        lminuto = new Label("Minutos");
        lmilessimo = new Label("Milessimos");
        // criar os campos de texto para o painel
        tnome_vei = new TextField(10);
        tnome_vei.addFocusListener(this);
        //tcod_equi.setEditable(false);
        tnome_equi = new TextField(50);
        tnome_equi.addFocusListener(this);
        //tnome_equi.setEditable(false);
        tnome_pil = new TextField(50);
        tnome_pil.addFocusListener(this);
        tvolta1 = new TextField(15);
        tvolta1.setEnabled(false);
        tvolta2 = new TextField(15);
        tvolta2.setEnabled(false);
        tvoltatot = new TextField(15);
        tvoltatot.setEnabled(false);
        tcodigo_eqi = new TextField(5);
        tminuto = new TextField();
        tsegundo = new TextField();
        tmilessimo = new TextField();
        // posiciona e determina o tamanho dos elementos
        //rótulos e campo de texto do painel
        lnome_vei.setBounds(10, 15, 85, 13);
        tnome_vei.setBounds(95, 15, 50, 19);
        lnome_equi.setBounds(10, 40, 85, 13);
        tnome_equi.setBounds(95, 40, 240, 19);
        lnome_pil.setBounds(10, 65, 85, 13);
        tnome_pil.setBounds(95, 65, 240, 19);
        lvolta1.setBounds(10, 95, 45, 13);
        tvolta1.setBounds(10, 110, 60, 19);
        lvolta2.setBounds(75, 95, 45, 13);
        tvolta2.setBounds(75, 110, 60, 19);
        lminuto.setBounds(35, 160, 50, 13);
        tminuto.setBounds(35, 175, 50, 19);
        lsegundo.setBounds(100, 160, 60, 13);
        tsegundo.setBounds(100, 175, 50, 19);
        lmilessimo.setBounds(165, 160, 65, 13);
        tmilessimo.setBounds(165, 175, 50, 19);
        lvoltatot.setBounds(140, 95, 69, 13);
        tvoltatot.setBounds(140, 110, 60, 19);

        //adiciona labels e campos ao painel
        painel.add(lnome_vei);
        painel.add(tnome_vei);
        painel.add(lnome_equi);
        painel.add(tnome_equi);
        painel.add(lnome_pil);
        painel.add(tnome_pil);
        painel.add(lvolta1);
        painel.add(tvolta1);
        painel.add(lvolta2);
        painel.add(tvolta2);
        painel.add(lvoltatot);
        painel.add(tvoltatot);
        painel.add(lminuto);
        painel.add(tminuto);
        painel.add(lsegundo);
        painel.add(tsegundo);
        painel.add(lmilessimo);
        painel.add(tmilessimo);
        //cria botões;
        bIniciar = new Button("Iniciar");
        bIniciar.addActionListener(this);
        bSalvar = new Button("Salvar");
        bSalvar.addActionListener(this);
        bPausar = new Button("Pausar");
        bPausar.addActionListener(this);
        bRedefinir = new Button("Redefinir");
        bRedefinir.addActionListener(this);
        bConsultar = new Button ("Consultar Resgistro");
        bConsultar.addActionListener(this);
        bConsultar.setSize(400,100);
        // define o gerenciador de layout para o painel de botões
        painelbot.setLayout(new FlowLayout());
        //adiciona os botões ao painel
        painelbot.add(bIniciar);
        painelbot.add(bSalvar);
        painelbot.add(bPausar);
        // adiciona o botao consultar ao painel
        painelcon.setLayout(new FlowLayout());
        painelcon.add(bConsultar);
        //adiciona os paineis e o menu à janela
        janela.add(painelbot);
        janela.add(painel);
        janela.setMenuBar(menuprin);
        janela.add(painelcon);

        minuto = new minuto();
        segundo = new segundo();
        milessimo = new milessimo();
        tmin = new Thread(minuto);
        tseg = new Thread(segundo);
        tmili = new Thread(milessimo);
        tminuto.setText("0");
        tsegundo.setText("0");
        tmilessimo.setText("0");
        clicar = 0;
        bSalvar.setEnabled(false);
        bPausar.setEnabled(false);
       
    }


    //metodos acessores
    public void setTcodigo_eqi(String codigo_eqi){
        tcodigo_eqi.setText(codigo_eqi);
    }
    public void setTnome_vei(String nome_vei) {
        tnome_vei.setText(nome_vei);
    }

    public void setTnome_equi(String nome_equi) {
        tnome_equi.setText(nome_equi);
    }

    public void setTnome_pil(String nome_pil) {
        tnome_pil.setText(nome_pil);
    }

    public void setTvolta1(String volta1) {
        tvolta1.setText(volta1);
    }

    public void setTvolta2(String volta2) {
        tvolta2.setText(volta2);
    }

    public void setTvoltatot(String voltatot) {
        tvoltatot.setText(voltatot);
    }

    public void setMenuBar(MenuBar menuprin) {
        janela.setMenuBar(menuprin);
    }

    public void setTminuto(String minuto) {
        tminuto.setText(minuto);
    }

    public void setTsegundo(String segundo) {
        tsegundo.setText(segundo);
    }

    public void setTmilessimo(String milessimo) {
        tmilessimo.setText(milessimo);
    }

    //metodos mutadores
    public String getTcodigo_eqi(){
        return tcodigo_eqi.getText();
    }
    public String getTnome_vei() {
        return tnome_vei.getText();
    }

    public String getTnome_equi() {
        return tnome_equi.getText();
    }

    public String getTnome_pil() {
        return tnome_pil.getText();
    }

    public String getTvolta1() {
        return tvolta1.getText();
    }

    public String getTvolta2() {
        return tvolta2.getText();
    }

    public String getTvoltatot() {
        return tvoltatot.getText();
    }

    public MenuBar getMenuBar() {
        return janela.getMenuBar();
    }

    public String getTmilessimo() {
        return tmilessimo.getText();
    }

    public String getTminuto() {
        return tminuto.getText();
    }

    public String getTsegundo() {
        return tsegundo.getText();
    }

    public void motrarpainel() {
        painel.setSize(350, 500);
        painel.setLocation(325, 80);
        painelbot.setSize(250, 40);
        painelbot.setLocation(350, 285);
        painel.setVisible(true);
        painelbot.setVisible(true);
        painelcon.setVisible(false);
    }
    public void mostrarpainel2(){
       
        painelcon.setSize(600,530);
        painelcon.setLocation(120, 55);
        painel.setVisible(false);
        painelbot.setVisible(false);
        painelcon.setVisible(true);
    
    }

    public void mostraCronometro() {
        janela.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(m11)) {
            this.motrarpainel();
            
        }
        if(e.getSource().equals(m12)){
            this.mostrarpainel2();
            
        }
        if (e.getSource().equals(m13)) {
            System.exit(0);
        }
        Button b = (Button) e.getSource();
        if (b == bSalvar) this.botaoSalvar();
        if (b == bIniciar) this.botaoIniciar();
        if (b == bPausar) this.botaoPausar();
        if(b ==bConsultar) this.botaoConsultar();
        
    }
    

    public void botaoIniciar() {
        tmin.start();
        tseg.start();
        tmili.start();
        bIniciar.setEnabled(false);
        bSalvar.setEnabled(true);
        bPausar.setEnabled(true);
        bRedefinir.setEnabled(true);
        m12.setEnabled(false);
    }

    public void botaoPausar() {
        if (clicar == 0) {
            cronometroaps.minuto.pausar();
            cronometroaps.segundo.pausar();
            cronometroaps.milessimo.pausar();
            clicar = 1;
        } else {
            cronometroaps.minuto.retomar();
            cronometroaps.segundo.retomar();
            cronometroaps.milessimo.retomar();
            clicar = 0;
        }
        if (clicar == 1) {
            bSalvar.setEnabled(false);
        } else {
            bSalvar.setEnabled(true);
        }
        m12.setEnabled(true);
    }

    Connection conecta(){
        String url ="jdbc:mysql://127.0.0.1:3306/aps_3cc?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String usuario = "root";
        String senha = "sucodeuva12";
        Connection con;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, usuario, senha);
                return con;
            }catch(ClassNotFoundException cnf) {
                JOptionPane.showMessageDialog(null, "Houve um erro no DRIVER:"+cnf.getMessage());
                return null;
            } catch (SQLException sql) {
                JOptionPane.showMessageDialog(null,"Houve um erro no DRIVER"+sql);
                return null;
            }
    }

    public void botaoSalvar() {
        String tempoVolta = "";
        if (clicar == 0) {
            tempoVolta = getTminuto() + ":" + getTsegundo() + ":" + getTmilessimo();
            setTvolta1(tempoVolta);
            clicar = 1;
        } else {
            String tempoVolta1 = getTvolta1();
            String tempoVolta2 = getTminuto() + ":" + getTsegundo() + ":" + getTmilessimo();
            long tempo1Milis = parseTempoMilissegundos(tempoVolta1);
            long tempo2Milis = parseTempoMilissegundos(tempoVolta2);
            long diferencaMili = tempo2Milis - tempo1Milis;
            String tempoSegundaVolta = formatarTempoMili(diferencaMili);
            setTvolta2(tempoSegundaVolta);
            long somaMili = tempo1Milis + diferencaMili;
            String tempoTot = formatarTempoMili(somaMili);
            setTvoltatot(tempoTot);
            clicar++;

            Connection con = conecta();
            try {
                Statement st = con.createStatement();
                int resultado =st.executeUpdate("insert into registrovolta (nome_veiculo, nome_equipe, nome_piloto, volta1, volta2, voltatot) values('"+getTnome_vei()+"','"+getTnome_equi()+"','"+getTnome_pil()+"','"+getTvolta1()+"','"+getTvolta2()+"','"+getTvoltatot()+"')");
                st.close();
                con.close();
                JOptionPane.showMessageDialog(null, "Agendamento finalizado com sucesso!");
                this.limpaDados();
                JOptionPane.showMessageDialog(null, "Reinicie a aplicação :)");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Não foi possível salvar o Registro");
            }
        }
    }
    public void botaoConsultar(){
        Connection con = conecta();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from registrovolta order by voltatot asc");
            while(rs.next()){
            String cod_equi = rs.getString("codigoeq");
            String nome_vei = rs.getString("nome_veiculo");
            String nome_equi = rs.getString("nome_equipe");
            String nome_pil = rs.getString("nome_piloto");
            String volta1 = rs.getString("volta1");
            String volta2 = rs.getString("volta2");
            String voltatot = rs.getString("voltatot");
            tabelamold.addRow(new Object[]{cod_equi,nome_vei, nome_equi, nome_pil, volta1, volta2, voltatot});
            }
            JOptionPane.showMessageDialog(null, "Consulta realizada com Sucesso!");
            con.close();
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null,"Não foi possível realizar a consulta");
        }
        bConsultar.setEnabled(false);
        
    }
    public void limpaDados(){
        cronometroaps.minuto.pausar();
        cronometroaps.segundo.pausar();
        cronometroaps.milessimo.pausar();
        cronometroaps.minuto.zerar();
        cronometroaps.segundo.zerar();
        cronometroaps.milessimo.zerar();
        this.setTnome_vei("");
        this.setTnome_equi("");
        this.setTnome_pil("");
        this.setTvolta1("");
        this.setTvolta2("");
        this.setTvoltatot("");
        this.setTminuto("0");
        this.setTsegundo("0");
        this.setTmilessimo("0");
        bIniciar.setEnabled(false);
        bSalvar.setEnabled(false);
        bPausar.setEnabled(false);
        tnome_vei.setEnabled(false);
        tnome_equi.setEnabled(false);
        tnome_pil.setEnabled(false);
        tminuto.setEnabled(false);
        tsegundo.setEnabled(false);
        tmilessimo.setEnabled(false);
        
    }
    
    public String formatarTempoMili(long diferencaMili) {
        long minutos = diferencaMili / (60 * 1000);
        long segundos = (diferencaMili % (60 * 1000))/1000;
        long milessimos = (diferencaMili % (1000));
        return String.format("%02d:%02d:%02d", minutos, segundos, milessimos);
    }

    public long parseTempoMilissegundos(String tempoVolta1) {
       String [] partes = tempoVolta1.split(":");
       long minutos = Long.parseLong(partes[0]);
       long segundos = Long.parseLong(partes[1]);
       long milessimos = Long.parseLong(partes[2]);
        return (minutos * 60 *1000)+(segundos *1000)+ milessimos;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }


    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Nada a fazer aqui
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nada a fazer aqui
    }
    

    public static void main(String[] args) {
        bloquearPrint bloquearPrint = new bloquearPrint();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(bloquearPrint);
        telateste telateste = new telateste();
        telateste.mostraCronometro();
    }
}
