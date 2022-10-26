package ldap.example;

import java.util.Hashtable;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;

public class LDAPExample {

    public static boolean authenticate(String ldapDn, String ldapPassword) throws Exception {

        // Configuracion del entorno para crear el contexto inicial
        Hashtable<String, String> entorno = new Hashtable<String, String>();
        entorno.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        entorno.put(Context.PROVIDER_URL, "ldap://cxnetworks.net:389/DC=cxnetworks,DC=net"); //tworks.net:389/DC=cxnetworks,DC=net
        entorno.put(Context.SECURITY_AUTHENTICATION, "simple");
        entorno.put(Context.SECURITY_PRINCIPAL, ldapDn + "@neoris.com");
        entorno.put(Context.SECURITY_CREDENTIALS, ldapPassword);

        DirContext contexto = null;

        try {
            // Se establece la conexion con LDAP
            contexto = new InitialDirContext(entorno);
            contexto.close();
            return true;
        } catch (AuthenticationException authEx) {
            //Usuario o password incorrectos
            authEx.printStackTrace();
            return false;

        } catch (Exception e) {
            throw e;
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println(authenticate("johnny.deep", "passwordhere"));

    }

}
