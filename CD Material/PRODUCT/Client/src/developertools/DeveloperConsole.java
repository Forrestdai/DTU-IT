/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package developertools;

import common.interfaces.ProcessorRequest;
import connection.udp.UDPListener;
import execute.Client;
import execute.States;
import java.awt.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author JamesFoxes
 */
public class DeveloperConsole extends javax.swing.JFrame
{

    private ConcurrentMap<Integer, Client> clients = new ConcurrentHashMap<>();
    private DefaultMutableTreeNode listeningClients = new DefaultMutableTreeNode("Listening");
    private DefaultMutableTreeNode sendingClients = new DefaultMutableTreeNode("Sending");
    private DefaultMutableTreeNode loggedClients = new DefaultMutableTreeNode("LoggedIn");
    private DefaultMutableTreeNode goneClients = new DefaultMutableTreeNode("Gone");
    private DefaultMutableTreeNode clientTreeTop = new DefaultMutableTreeNode("Clients");
    private Client selectedClient;
    
    private int clientIndex = 1;

    public DeveloperConsole()
    {
        initComponents();
        startUpdaterThread();
        clientTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    public void redrawTree()
    {
        revalidate();
        repaint();
    }

    public static void main(String args[])
    {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(DeveloperConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DeveloperConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DeveloperConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DeveloperConsole.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new DeveloperConsole().setVisible(true);
            }
        });
    }

    private void startUpdaterThread()
    {
        Executors.newSingleThreadExecutor().submit(new Runnable()
        {

            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(500);
                        contstructTree();
                    } catch (InterruptedException ex)
                    {
                    }
                }
            }

            private void contstructTree()
            {
                listeningClients.removeAllChildren();
                sendingClients.removeAllChildren();
                loggedClients.removeAllChildren();
                goneClients.removeAllChildren();
                for (Map.Entry<Integer, Client> entry : clients.entrySet())
                {
                    Client key = entry.getValue();
                    States value = key.data.clientState;
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode("client: " + key.data.clientID);
                    switch (value)
                    {
                        case IDLE:
                            listeningClients.add(node);
                            break;
                        case SENDING:
                            sendingClients.add(node);
                            break;
                        case LOGGEDIN:
                            loggedClients.add(node);
                            break;
                        case GONE:
                            goneClients.add(node);
                            break;
                    }
                }

                DefaultTreeModel model = (DefaultTreeModel) clientTree.getModel();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
                root.add(listeningClients);
                root.add(sendingClients);
                root.add(loggedClients);
                root.add(goneClients);
                model.reload(clientTreeTop);
                for (int i = 0; i < clientTree.getRowCount(); i++)
                {
                    clientTree.expandRow(i);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        startClient_btn = new javax.swing.JButton();
        inputAmountOfClientsToStart = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientTree = new javax.swing.JTree();
        remove_btn = new javax.swing.JButton();
        removeClientIndex = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Amount of Clients to start");

        startClient_btn.setText("START");
        startClient_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                startClient_btnActionPerformed(evt);
            }
        });

        inputAmountOfClientsToStart.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                inputAmountOfClientsToStartActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Clients");
        clientTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        clientTree.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                clientTreeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(clientTree);

        remove_btn.setText("remove");
        remove_btn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                remove_btnActionPerformed(evt);
            }
        });

        jLabel2.setText("Remove client");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputAmountOfClientsToStart, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(startClient_btn)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(remove_btn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(removeClientIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeClientIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(remove_btn))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(startClient_btn)
                            .addComponent(inputAmountOfClientsToStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(161, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startClient_btnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startClient_btnActionPerformed
    {//GEN-HEADEREND:event_startClient_btnActionPerformed
        for (int i = 0; i < Integer.parseInt(inputAmountOfClientsToStart.getText()); ++i)
        {
            clients.put(clientIndex, new Client());
            clientIndex++;
        }
    }//GEN-LAST:event_startClient_btnActionPerformed

    private void inputAmountOfClientsToStartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inputAmountOfClientsToStartActionPerformed
    {//GEN-HEADEREND:event_inputAmountOfClientsToStartActionPerformed

    }//GEN-LAST:event_inputAmountOfClientsToStartActionPerformed

    private void clientTreeMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_clientTreeMouseClicked
    {//GEN-HEADEREND:event_clientTreeMouseClicked
    }//GEN-LAST:event_clientTreeMouseClicked

    private void remove_btnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_remove_btnActionPerformed
    {//GEN-HEADEREND:event_remove_btnActionPerformed
        Client client = clients.get(Integer.parseInt(removeClientIndex.getText()));
        client.setState(States.GONE);
    }//GEN-LAST:event_remove_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree clientTree;
    private javax.swing.JTextField inputAmountOfClientsToStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField removeClientIndex;
    private javax.swing.JButton remove_btn;
    private javax.swing.JButton startClient_btn;
    // End of variables declaration//GEN-END:variables
}
