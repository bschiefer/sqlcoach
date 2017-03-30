/*******************************************************************************
 * This file is part of SQLCoach.
 *
 * SQLCoach is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SQLCoach is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.sqlcoach.remoteEJB;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * http://www.mastertheboss.com/jboss-server/jboss-as-7/jboss-as-7-remote-ejb-client-tutorial
 * https://docs.jboss.org/author/display/AS71/EJB+invocations+from+a+remote+client+using+JNDI
 * 
 * @author SQLCoach
 *
 */
public class DBRemoteEJBClient {
	private static final Logger LOG = LoggerFactory.getLogger(DBRemoteEJBClient.class);
	
	public static <T> T getEJB(String getNameFromInterface, String getSimpleNameFromBean) throws NamingException {
		final T ejb = lookupRemoteEJB(getNameFromInterface, getSimpleNameFromBean);

		return ejb;
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T lookupRemoteEJB(String getNameFromInterface, String getSimpleNameFromBean) throws NamingException {
		final Hashtable<String, String> jndiProperties = new Hashtable<String, String>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		final Context context = new InitialContext(jndiProperties);
		final String appName = "sqlcoach-ear";
		final String moduleName = "sqlcoach-ejb";
		final String beanName = getSimpleNameFromBean;
		final String viewClassName = getNameFromInterface;

		String jndiName = "ejb:" + appName + "/" + moduleName + "/" + beanName + "!" + viewClassName;
		LOG.info("Looking EJB via JNDI ");
		LOG.info(jndiName);
		 
		return (T) context.lookup(jndiName);
	}
}
