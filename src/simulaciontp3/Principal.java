package simulaciontp3;

import java.util.LinkedList;
import java.util.Iterator;
import Clases.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

public class Principal extends javax.swing.JFrame {

    LinkedList lista = new LinkedList();
    LinkedList listaOcurrencias = new LinkedList();
    LinkedList listaFreqEsp = new LinkedList();
    LinkedList listaFreqObs = new LinkedList();
    double minimo, maximo, actual, distancia, paso;

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
    }

    /*limpia las listas con los valores*/
    private void limpiarListas() {
        lista.clear();
        listaOcurrencias.clear();
        listaFreqEsp.clear();
        listaFreqObs.clear();
    }

    /*limpia las tablas*/
    private void limpiarTablas() {
        ((DefaultTableModel) Principal.this.table_normal.getModel()).setRowCount(0);
        ((DefaultTableModel) Principal.this.table_ocurrencias_normal.getModel()).setRowCount(0);
        ((DefaultTableModel) Principal.this.table_uniforme.getModel()).setRowCount(0);
        ((DefaultTableModel) Principal.this.table_ocurrencias_uniforme.getModel()).setRowCount(0);
        ((DefaultTableModel) Principal.this.table_exponencial.getModel()).setRowCount(0);
        ((DefaultTableModel) Principal.this.table_ocurrencias_exponencial.getModel()).setRowCount(0);
    }

    /*dada la cantidad de intervalos calcula el valor maximo y minimo de la coleccion de numeros generados 
    y luego calcula la distancia entre estos y el tamanio del paso para los intervalos*/
    private void setMinimoYMaximo(int intervalos) {
        this.minimo = Double.parseDouble(lista.get(0).toString());
        this.maximo = Double.parseDouble(lista.get(0).toString());

        /*Iteramos sobre la lista y buscamos el valor maximo y minimo*/
        for (Object object : lista) {

            this.actual = Double.parseDouble(object.toString());

            if (minimo > actual) {
                minimo = actual;
            }
            if (maximo < actual) {
                maximo = actual;
            }
        }
        /*calculamos el ancho del intevalo generad*/
        this.distancia = maximo - minimo;
        /*Calculamos el tamanio del pasa*/
        this.paso = distancia / intervalos;
    }

    /*cuenta cuantas ocurrencias hay en cada intervalo*/
    private void setListaOcurrencias(int intervalos) {
        double ocurrencia;
        double pasoTemp = paso;
        double minimoTemp = minimo;
        for (int i = 0; i < intervalos; i++) {
            int cont = 0;
            for (Object aleatorio : lista) {
                ocurrencia = Double.parseDouble(aleatorio.toString());
                if (ocurrencia > minimoTemp && ocurrencia < (minimoTemp + pasoTemp)) {
                    cont++;
                }
            }
            listaOcurrencias.add(i, cont);
            minimoTemp += pasoTemp;
        }
    }

    /*carga una lista con las frecuencias observadas de cada intervalo*/
    private void setListaFreqObs(int intervalos, int cantidad) {
        for (int i = 0; i < intervalos; i++) {
            listaFreqObs.add(i, Double.parseDouble(listaOcurrencias.get(i).toString()));
        }
    }

    /*calcula la frecuencia esperada para cada intervalo para la distribucion Normal*/
    private void setListaFreqEspNorm(int intervalos, float media, float desviacion, int cantidad) {
        NormalDistribution normalDist = new NormalDistribution(media, desviacion);
        double pasoTemp = paso;
        double minimoTemp = minimo;
        for (int i = 0; i < intervalos; i++) {
            listaFreqEsp.add(i, (normalDist.probability(minimoTemp, minimoTemp + pasoTemp))*cantidad);
            minimoTemp += pasoTemp;
        }
    }

    /*calcula la frecuencia esperada para cada intervalo para la distribucion Normal*/
    private void setListaFreqEspExp(int intervalos, double lambda, int cantidad) {
        ExponentialDistribution expDist = new ExponentialDistribution((1 / (lambda)));
        //System.out.println(expDist.getMean() + " / " + expDist.getNumericalMean() + " / " + expDist.getNumericalVariance());
        double pasoTemp = paso;
        double minimoTemp = minimo;
        for (int i = 0; i < intervalos; i++) {
            // System.out.println(minimoTemp + " /" + minimoTemp + pasoTemp);
            listaFreqEsp.add(i, (expDist.probability(minimoTemp, minimoTemp + pasoTemp)*cantidad));
            minimoTemp += pasoTemp;
        }
    }

    /*calcula la frecuencia esperada para cada intervalo para la distribucion Normal*/
    private void setListaFreqEspUni(int intervalos, int cantidad) {

        double pasoTemp = paso;
        double minimoTemp = minimo;
        for (int i = 0; i < intervalos; i++) {
           // System.out.println(minimoTemp + " /" + minimoTemp + pasoTemp);
            listaFreqEsp.add(i, ((double) cantidad/(double) intervalos));
            minimoTemp += pasoTemp;

        }
    }
    
    /*Realiza la prueba de chi CUadrado, recibe las listas de frecuencias observadas y esperadas*/
    public double chiCuadrado(LinkedList obs, LinkedList esp) {
        double resultado = 0;
        Iterator iter = obs.listIterator();
        Iterator iter2 = esp.listIterator();
        /*itera sobre ambas listas y acumula el resultado de la operacin de chi en cada intervalo*/
        while (iter.hasNext()) {
            double espTemp = (Double) iter2.next();
            resultado += (Math.pow(((Double) iter.next() - espTemp), 2)) / espTemp;
        }
        return resultado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        integrantes = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        btn_generar_exponencial = new javax.swing.JButton();
        txt_precision = new javax.swing.JTextField();
        txt_lambda_exponencial = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_exponencial = new javax.swing.JTable();
        txt_cantidad_exponencial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table_ocurrencias_exponencial = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txt_minimo_exponencial = new javax.swing.JTextField();
        txt_paso_exponencial = new javax.swing.JTextField();
        txt_distancia_exponencial = new javax.swing.JTextField();
        txt_maximo_exponencial = new javax.swing.JTextField();
        txt_intervalos_exponencial = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_chi_exponencial = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txt_grados_exp = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_cantidad_normal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_media = new javax.swing.JTextField();
        txt_desviacion_estandar = new javax.swing.JTextField();
        btn_generar_normal = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_precision_normal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_normal = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        table_ocurrencias_normal = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txt_maximo_normal = new javax.swing.JTextField();
        txt_distancia_normal = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_minimo_normal = new javax.swing.JTextField();
        txt_paso_normal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_intervalos_normal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_chi_normal = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txt_grados_norm = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_generar_uniforme = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_uniforme = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        txt_limite_inferior = new javax.swing.JTextField();
        txt_cantidad_uniforme = new javax.swing.JTextField();
        txt_limite_superior = new javax.swing.JTextField();
        txt_intervalos_uniforme = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        table_ocurrencias_uniforme = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        txt_maximo_uniforme = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_minimo_uniforme = new javax.swing.JTextField();
        txt_paso_uniforme = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txt_distancia_uniforme = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_chi_uniforme = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_grados_uni = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        integrantes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                integrantesStateChanged(evt);
            }
        });
        integrantes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                integrantesFocusLost(evt);
            }
        });

        btn_generar_exponencial.setText("Generar");
        btn_generar_exponencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generar_exponencialActionPerformed(evt);
            }
        });

        table_exponencial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "n", "Rnd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(table_exponencial);

        txt_cantidad_exponencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cantidad_exponencialActionPerformed(evt);
            }
        });

        jLabel8.setText("Precisión");

        jLabel9.setText("Lambda");

        jLabel10.setText("Numeros a generar");

        table_ocurrencias_exponencial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro Intervalo", "Frecuencia Observada", "Frecuencia Esperad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(table_ocurrencias_exponencial);

        jLabel11.setText("Intervalos");

        txt_minimo_exponencial.setEditable(false);

        txt_paso_exponencial.setEditable(false);

        txt_distancia_exponencial.setEditable(false);

        txt_maximo_exponencial.setEditable(false);

        jLabel12.setText("Distancia");

        jLabel13.setText("Valor minimo");

        jLabel14.setText("Valor maximo");

        jLabel15.setText("Paso");

        jLabel5.setText("Chi Cuadrado");

        txt_chi_exponencial.setEditable(false);

        jLabel29.setText("Grados de libertad");

        txt_grados_exp.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_intervalos_exponencial, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                    .addComponent(txt_lambda_exponencial)
                                    .addComponent(txt_cantidad_exponencial)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_grados_exp, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precision, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_generar_exponencial))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_maximo_exponencial)
                                    .addComponent(txt_paso_exponencial)
                                    .addComponent(txt_distancia_exponencial)
                                    .addComponent(txt_chi_exponencial)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_minimo_exponencial, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btn_generar_exponencial))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txt_cantidad_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_precision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel13)
                            .addComponent(txt_minimo_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txt_lambda_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txt_maximo_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txt_paso_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(txt_intervalos_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_distancia_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_chi_exponencial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29)
                    .addComponent(txt_grados_exp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addContainerGap())
        );

        integrantes.addTab("Exponencial Negativa", jPanel3);

        jLabel1.setText("Numeros a generar");

        jLabel2.setText("Media ");

        jLabel3.setText("Desviacion estandar");

        btn_generar_normal.setText("Generar");
        btn_generar_normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generar_normalActionPerformed(evt);
            }
        });

        jLabel4.setText("Precision");

        table_normal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "n", "Rnd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_normal);

        table_ocurrencias_normal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Intervalos", "Frecuencia Observada", "Frecuencua Esperada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(table_ocurrencias_normal);

        jLabel16.setText("Valor maximo");

        txt_maximo_normal.setEditable(false);

        txt_distancia_normal.setEditable(false);

        jLabel17.setText("Distancia");

        jLabel18.setText("Paso");

        jLabel19.setText("Valor minimo");

        txt_minimo_normal.setEditable(false);

        txt_paso_normal.setEditable(false);

        jLabel7.setText("Intervalos");

        jLabel6.setText("Chi Cuadrado");

        txt_chi_normal.setEditable(false);

        jLabel30.setText("Grados de libertad");

        txt_grados_norm.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3))
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addGap(66, 66, 66)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_grados_norm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_intervalos_normal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_desviacion_estandar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_cantidad_normal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_media, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_generar_normal)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_precision_normal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(22, 22, 22)
                        .addComponent(txt_minimo_normal))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(58, 58, 58)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_maximo_normal)
                            .addComponent(txt_paso_normal)
                            .addComponent(txt_distancia_normal)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(16, 16, 16)
                        .addComponent(txt_chi_normal)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_cantidad_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txt_precision_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_media, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_desviacion_estandar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(btn_generar_normal)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(txt_distancia_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(txt_intervalos_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_minimo_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_maximo_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(txt_paso_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_chi_normal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(txt_grados_norm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap())
        );

        integrantes.addTab("Normal", jPanel1);

        btn_generar_uniforme.setText("Generar");
        btn_generar_uniforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generar_uniformeActionPerformed(evt);
            }
        });

        table_uniforme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "n", "Rnd"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_uniforme);

        jLabel20.setText("Numeros a generar:");

        txt_cantidad_uniforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cantidad_uniformeActionPerformed(evt);
            }
        });

        txt_intervalos_uniforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_intervalos_uniformeActionPerformed(evt);
            }
        });

        jLabel21.setText("Limite Inferior:");

        jLabel22.setText("Limite Superior:");

        jLabel23.setText("Intervalos:");

        table_ocurrencias_uniforme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IntervalosNro Intervalo", "Frecuencia Observada", "Frecuencia Esperada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(table_ocurrencias_uniforme);

        jLabel24.setText("Valor maximo");

        txt_maximo_uniforme.setEditable(false);

        jLabel25.setText("Valor minimo");

        txt_minimo_uniforme.setEditable(false);

        txt_paso_uniforme.setEditable(false);

        jLabel26.setText("Paso");

        jLabel27.setText("Distancia");

        txt_distancia_uniforme.setEditable(false);

        jLabel28.setText("Chi Cuadrado");

        txt_chi_uniforme.setEditable(false);

        jLabel31.setText("Grados de libertad");

        txt_grados_uni.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_grados_uni)
                            .addComponent(txt_limite_inferior, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(txt_limite_superior, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(txt_intervalos_uniforme, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(txt_cantidad_uniforme))
                        .addGap(35, 35, 35)
                        .addComponent(btn_generar_uniforme)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel27))
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_maximo_uniforme)
                            .addComponent(txt_minimo_uniforme)
                            .addComponent(txt_paso_uniforme)
                            .addComponent(txt_distancia_uniforme)
                            .addComponent(txt_chi_uniforme))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(txt_cantidad_uniforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_generar_uniforme)
                        .addComponent(txt_minimo_uniforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_limite_inferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(txt_maximo_uniforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_paso_uniforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_distancia_uniforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txt_limite_superior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_intervalos_uniforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txt_grados_uni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_chi_uniforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                    .addComponent(jScrollPane6))
                .addContainerGap())
        );

        integrantes.addTab("Uniforme", jPanel2);

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Grupo 7\n\nJaime, Eduardo\t\t54023\nMigotti, Franco\t\t60547\nRivarossa, Luciana\t60354\nRoth, Kevin\t\t64575\t");
        jScrollPane8.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(573, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(510, Short.MAX_VALUE))
        );

        integrantes.addTab("Integrantes", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(integrantes)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(integrantes, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_generar_normalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generar_normalActionPerformed
        /*Se limpian las listas y las tablas*/
        this.limpiarListas();
        this.limpiarTablas();

        try {
            int ab = Integer.parseInt(this.txt_desviacion_estandar.getText());
            if (ab >= 0) {

                int cantidad = Integer.parseInt(this.txt_cantidad_normal.getText());
                float media = Integer.parseInt(this.txt_media.getText());
                float desviacion = Integer.parseInt(this.txt_desviacion_estandar.getText());
                int intervalos = Integer.parseInt(this.txt_intervalos_normal.getText());
                int precision = Integer.parseInt(this.txt_precision_normal.getText());

                /*Inicializamos el generador*/
                GeneradorNormal normal = new GeneradorNormal(precision, media, desviacion);
                lista = normal.generarNumeroAleatorio(cantidad);

                /*Seteamos las variables Maximo y minimo al primer elemento de la lista*/
                this.setMinimoYMaximo(intervalos);

                /*Mostramos los valores maximos y minimos*/
                this.txt_minimo_normal.setText(Double.toString(minimo));
                this.txt_maximo_normal.setText(Double.toString(maximo));

                /*mostramos la distancia y el paso*/
                this.txt_distancia_normal.setText(Double.toString(distancia));
                this.txt_paso_normal.setText(Double.toString(paso));

                /*iteramos sobre la lista y buscamos a que intervalo corresponde cada numero generado*/
                this.setListaOcurrencias(intervalos);
                this.setListaFreqObs(intervalos, cantidad);
                this.setListaFreqEspNorm(intervalos, media, desviacion, cantidad);

                /*Calculamos Chi*/
                double chi = this.chiCuadrado(this.listaFreqObs, this.listaFreqEsp);
                this.txt_chi_normal.setText(String.valueOf(chi));
                System.out.println(intervalos-3);
                this.txt_grados_norm.setText(String.valueOf(intervalos-3));
                /*cargamos las tablas*/
                this.cargarTabla(2);
                this.cargarTablaOcurrencias(2);

                /*mostramos el histograma*/
                Histograma histo = new Histograma("Distribucion Normal", this.pasarListaAVector(lista), intervalos);
                //histo.pack();
                //histo.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "El valor de la media no puede ser negativo", "Error", 1);
                this.txt_desviacion_estandar.setText("");
            }
        } catch (java.lang.NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Solo numeros en los campos", "Error", 1);

        }


    }//GEN-LAST:event_btn_generar_normalActionPerformed

    private void integrantesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_integrantesFocusLost

    }//GEN-LAST:event_integrantesFocusLost

    private void integrantesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_integrantesStateChanged
        ((DefaultTableModel) Principal.this.table_normal.getModel()).setRowCount(0);
        ((DefaultTableModel) Principal.this.table_uniforme.getModel()).setRowCount(0);
        ((DefaultTableModel) Principal.this.table_exponencial.getModel()).setRowCount(0);
        lista.clear();
    }//GEN-LAST:event_integrantesStateChanged

    private void btn_generar_exponencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generar_exponencialActionPerformed
        /*Se limpian las listas y las tablas*/
        this.limpiarListas();
        this.limpiarTablas();
        try {
            float ab = Float.parseFloat(this.txt_lambda_exponencial.getText());
            if (ab < 0) {
                JOptionPane.showMessageDialog(null, "El valor de lambda no puede ser negativo", "Error", 1);
                this.txt_lambda_exponencial.setText("");
                return;
            }

            int cantidad = Integer.parseInt(this.txt_cantidad_exponencial.getText());
            int intervalos = Integer.parseInt(this.txt_intervalos_exponencial.getText());
            float lambda = Float.parseFloat(this.txt_lambda_exponencial.getText());
            int precision = Integer.parseInt(this.txt_precision.getText());

            /*Inicializamos el generador*/
            GeneradorExponencialNegativa poi = new GeneradorExponencialNegativa(precision, lambda);
            lista = poi.generarNumeroAleatorio(cantidad);

            /*Seteamos las variables Maximo y minimo al primer elemento de la lista*/
            this.setMinimoYMaximo(intervalos);

            /*Mostramos los valores maximos y minimos*/
            /*mostramos la distancia y el paso*/
            this.txt_minimo_exponencial.setText(Double.toString(minimo));
            this.txt_maximo_exponencial.setText(Double.toString(maximo));
            this.txt_distancia_exponencial.setText(Double.toString(distancia));
            this.txt_paso_exponencial.setText(Double.toString(paso));

            /*iteramos sobre la lista y buscamos a que intervalo corresponde cada numero generado*/
            this.setListaOcurrencias(intervalos);
            this.setListaFreqObs(intervalos, cantidad);
            this.setListaFreqEspExp(intervalos, lambda, cantidad);

            /*Calculamos Chi*/
            double chi = this.chiCuadrado(this.listaFreqObs, this.listaFreqEsp);
            this.txt_chi_exponencial.setText(String.valueOf(chi));
            System.out.println(intervalos-2);
            this.txt_grados_exp.setText(String.valueOf(intervalos-2));
            
            /*cargamos las tablas*/
            this.cargarTabla(1);
            this.cargarTablaOcurrencias(1);

            Histograma histo = new Histograma("Distribucion Exponencial negativa", this.pasarListaAVector(lista), intervalos);
            //histo.pack();
            //histo.setVisible(true);

        } catch (java.lang.NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Solo numeros en los campos", "Error", 1);

        }
}//GEN-LAST:event_btn_generar_exponencialActionPerformed

    private void txt_cantidad_exponencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cantidad_exponencialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantidad_exponencialActionPerformed

    private void txt_intervalos_uniformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_intervalos_uniformeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_intervalos_uniformeActionPerformed

    private void txt_cantidad_uniformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cantidad_uniformeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cantidad_uniformeActionPerformed

    private void btn_generar_uniformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generar_uniformeActionPerformed

        /*Se limpian las listas y las tablas*/
        this.limpiarListas();
        this.limpiarTablas();
        try {

            int superior = Integer.parseInt(this.txt_limite_superior.getText());
            int inferior = Integer.parseInt(this.txt_limite_inferior.getText());

            if (inferior > superior) {
                JOptionPane.showMessageDialog(null, "El valor del limite superior debe ser mayor al limite inferior", "Error", 1);
                this.txt_limite_superior.setText("");
                this.txt_limite_inferior.setText("");
                return;

            }

            int cantidad = Integer.parseInt(this.txt_cantidad_uniforme.getText());
            int intervalos = Integer.parseInt(this.txt_intervalos_uniforme.getText());

            /*Inicializamos el generador*/
            int presicion = 2;
            GeneradorUniforme uni = new GeneradorUniforme(inferior, superior);
            lista = uni.generarNumeroAleatorio(cantidad);

            /*Seteamos las variables Maximo y minimo al primer elemento de la lista*/
            this.setMinimoYMaximo(intervalos);

            /*Mostramos los valores maximos y minimos*/
            /*mostramos la distancia y el paso*/
            this.txt_minimo_uniforme.setText(Double.toString(minimo));
            this.txt_maximo_uniforme.setText(Double.toString(maximo));
            this.txt_distancia_uniforme.setText(Double.toString(distancia));
            this.txt_paso_uniforme.setText(Double.toString(paso));

            /*iteramos sobre la lista y buscamos a que intervalo corresponde cada numero generado*/
            this.setListaOcurrencias(intervalos);
            this.setListaFreqObs(intervalos, cantidad);
            this.setListaFreqEspUni(intervalos, cantidad);

            /*Calculamos Chi*/
            double chi = this.chiCuadrado(this.listaFreqObs, this.listaFreqEsp);
            this.txt_chi_uniforme.setText(String.valueOf(chi));
            System.out.println(intervalos-1);
            this.txt_grados_uni.setText(String.valueOf(intervalos-1));

            /*cargamos las tablas*/
            this.cargarTabla(3);
            this.cargarTablaOcurrencias(3);

            Histograma histo = new Histograma("Distribucion Uniforme", this.pasarListaAVector(lista), intervalos);
            // histo.pack();
            // histo.setVisible(true);
        } catch (java.lang.NumberFormatException e1) {
            JOptionPane.showMessageDialog(null, "Solo numeros en los campos", "Error", 1);

        }
    }//GEN-LAST:event_btn_generar_uniformeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    /*recive una lista, la itera y devuelve un vector con los mismos valores que la lista original*/
    public double[] pasarListaAVector(LinkedList lista) {
        Iterator iter = lista.listIterator();
        double[] numeros = new double[lista.size()];
        int i = 0;
        while (iter.hasNext()) {
            numeros[i] = (Double) iter.next();
            i++;
        }
        return numeros;
    }

    /*recive una lista, la itera y devuelve un vector con los mismos valores que la lista original, para valores Long*/
    public long[] pasarListaAVectorLong(LinkedList lista) {
        Iterator iter = lista.listIterator();
        long[] numeros = new long[lista.size()];
        int i = 0;
        while (iter.hasNext()) {
            numeros[i] = ((Double) iter.next()).longValue();
            i++;

        }
        return numeros;
    }

    /*Recive el indice de la tabla con los valores RND a cargar, itera sobre las listas introduciendo los valores segun corresponda*/
    public void cargarTabla(int n) {
        Iterator iter = lista.listIterator();
        int i = 1;
        while (iter.hasNext()) {
            /*armamos el vector cadena con 2 valores, primero el numero de orden y despues el valor RND generado*/
            String[] o = {"" + i, iter.next().toString()};
            switch (n) {
                case (1):
                    ((DefaultTableModel) Principal.this.table_exponencial.getModel()).addRow(o);

                    break;
                case (2):
                    ((DefaultTableModel) Principal.this.table_normal.getModel()).addRow(o);

                    break;
                case (3):
                    ((DefaultTableModel) Principal.this.table_uniforme.getModel()).addRow(o);
                    break;
            }
            i++;
        }
    }

   

    /*Recive el indice de la tabla con los valores de las ocurrencias y frecuencias, itera sobre las listas introduciendo los valores segun corresponda*/
    public void cargarTablaOcurrencias(int n) {
        Iterator iter = listaFreqObs.listIterator();
        Iterator iterpro = listaFreqEsp.listIterator();
        int i = 1;
        while (iter.hasNext()) {
            /*armamos el vector cadena con 3 valores, primero el numero de orden, despues la frecuencia observada del intervalo y la frecuencia esperada de cada intervalo*/
            String[] o = {"" + i, iter.next().toString(), iterpro.next().toString()};
            switch (n) {
                case (1):
                    ((DefaultTableModel) Principal.this.table_ocurrencias_exponencial.getModel()).addRow(o);
                    break;
                case (2):
                    ((DefaultTableModel) Principal.this.table_ocurrencias_normal.getModel()).addRow(o);
                    break;
                case (3):
                    ((DefaultTableModel) Principal.this.table_ocurrencias_uniforme.getModel()).addRow(o);
                    break;
            }
            i++;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_generar_exponencial;
    private javax.swing.JButton btn_generar_normal;
    private javax.swing.JButton btn_generar_uniforme;
    private javax.swing.JTabbedPane integrantes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTable table_exponencial;
    private javax.swing.JTable table_normal;
    private javax.swing.JTable table_ocurrencias_exponencial;
    private javax.swing.JTable table_ocurrencias_normal;
    private javax.swing.JTable table_ocurrencias_uniforme;
    private javax.swing.JTable table_uniforme;
    private javax.swing.JTextField txt_cantidad_exponencial;
    private javax.swing.JTextField txt_cantidad_normal;
    private javax.swing.JTextField txt_cantidad_uniforme;
    private javax.swing.JTextField txt_chi_exponencial;
    private javax.swing.JTextField txt_chi_normal;
    private javax.swing.JTextField txt_chi_uniforme;
    private javax.swing.JTextField txt_desviacion_estandar;
    private javax.swing.JTextField txt_distancia_exponencial;
    private javax.swing.JTextField txt_distancia_normal;
    private javax.swing.JTextField txt_distancia_uniforme;
    private javax.swing.JTextField txt_grados_exp;
    private javax.swing.JTextField txt_grados_norm;
    private javax.swing.JTextField txt_grados_uni;
    private javax.swing.JTextField txt_intervalos_exponencial;
    private javax.swing.JTextField txt_intervalos_normal;
    private javax.swing.JTextField txt_intervalos_uniforme;
    private javax.swing.JTextField txt_lambda_exponencial;
    private javax.swing.JTextField txt_limite_inferior;
    private javax.swing.JTextField txt_limite_superior;
    private javax.swing.JTextField txt_maximo_exponencial;
    private javax.swing.JTextField txt_maximo_normal;
    private javax.swing.JTextField txt_maximo_uniforme;
    private javax.swing.JTextField txt_media;
    private javax.swing.JTextField txt_minimo_exponencial;
    private javax.swing.JTextField txt_minimo_normal;
    private javax.swing.JTextField txt_minimo_uniforme;
    private javax.swing.JTextField txt_paso_exponencial;
    private javax.swing.JTextField txt_paso_normal;
    private javax.swing.JTextField txt_paso_uniforme;
    private javax.swing.JTextField txt_precision;
    private javax.swing.JTextField txt_precision_normal;
    // End of variables declaration//GEN-END:variables

    /*public int[] pasarListaAVector(LinkedList lista) {
        Iterator iter = lista.listIterator();
        int [] numeros = new int[lista.size()];
        int i = 0;
        while (iter.hasNext()) {
            numeros[i] = (Integer)iter.next();
            i++;

        }
        return numeros;
    }
     */
 
}
