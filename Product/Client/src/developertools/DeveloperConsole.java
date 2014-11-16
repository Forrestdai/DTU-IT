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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author JamesFoxes
 */
public class DeveloperConsole extends javax.swing.JFrame
{

    private ConcurrentMap<Client, States> clients = new ConcurrentHashMap<>();
    private DefaultMutableTreeNode listeningClients = new DefaultMutableTreeNode("Listening");
    private DefaultMutableTreeNode sendingClients = new DefaultMutableTreeNode("Sending");
    private DefaultMutableTreeNode loggedClients = new DefaultMutableTreeNode("LoggedIn");
    private DefaultMutableTreeNode clientTreeTop = new DefaultMutableTreeNode("Clients");
    private Client selectedClient;
    
    public DeveloperConsole()
    {
        initComponents();
        startUpdaterThread();
        clientTree.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
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
                        updateMap();
                        contstructTree();
                    } catch (InterruptedException ex)
                    {
                    }
                }
            }

            private void updateMap()
            {
                for (Map.Entry<Client, States> entry : clients.entrySet())
                {
                    Client key = entry.getKey();
                    States value = entry.getValue();
                    if (!value.equals(key.getData().clientState))
                    {
                        clients.replace(key, key.getData().clientState);
                    }
                }
            }

            private void contstructTree()
            {
                listeningClients.removeAllChildren();
                sendingClients.removeAllChildren();
                loggedClients.removeAllChildren();
                for (Map.Entry<Client, States> entry : clients.entrySet())
                {
                    States value = entry.getValue();
                    Client key = entry.getKey();
                    switch (value)
                    {
                        case IDLE:
                            listeningClients.add(new DefaultMutableTreeNode("client: " + key.data.clientID));
                            break;
                        case SENDING:
                            sendingClients.add(new DefaultMutableTreeNode("client: " + key.data.clientID));
                        case LOGGEDIN:
                            loggedClients.add(new DefaultMutableTreeNode("client: " + key.data.clientID));
                            break;
                    }
                }

                DefaultTreeModel model = (DefaultTreeModel) clientTree.getModel();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
                root.add(listeningClients);
                root.add(sendingClients);
                root.add(loggedClients);
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
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(startClient_btn)
                    .addComponent(inputAmountOfClientsToStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startClient_btnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_startClient_btnActionPerformed
    {//GEN-HEADEREND:event_startClient_btnActionPerformed
        for (int i = 0; i < Integer.parseInt(inputAmountOfClientsToStart.getText()); ++i)
        {
            clients.put(new Client(), States.IDLE);
        }
    }//GEN-LAST:event_startClient_btnActionPerformed

    private void inputAmountOfClientsToStartActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inputAmountOfClientsToStartActionPerformed
    {//GEN-HEADEREND:event_inputAmountOfClientsToStartActionPerformed

    }//GEN-LAST:event_inputAmountOfClientsToStartActionPerformed

    private void clientTreeMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_clientTreeMouseClicked
    {//GEN-HEADEREND:event_clientTreeMouseClicked
    }//GEN-LAST:event_clientTreeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree clientTree;
    private javax.swing.JTextField inputAmountOfClientsToStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton startClient_btn;
    // End of variables declaration//GEN-END:variables
}
