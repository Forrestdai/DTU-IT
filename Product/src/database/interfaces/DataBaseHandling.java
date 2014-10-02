/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database.interfaces;

/**
 *
 * @author James
 */
public interface DataBaseHandling
{
    public CopyableUser getUserInformationObject();
    public ProtectedUser getUserTransactionObject();
    public void updateUserInformation(CopyableUser user);
    public void updateUserTransactions(ProtectedUser user);
    public void createUser(CopyableUser user);
}
