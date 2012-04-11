package sqlbase;

/**
 * Handle session object, parameters, transactions.
 */
public class SessionFactory {
	
		public static final SessionFactory Instance = new SessionFactory();
		public Session session;
		/**
		 * 
		 */
		private SessionFactory() {
			//This constructor will never be invoked
		} 
		/**
		 * 
		 * @return
		 */
		public static SessionFactory getIns() {
			return Instance;
		}
		/**
		 * 
		 * @param session
		 */
		void registerSession(Session session) {
			Instance.session = session;
		}
		/**
		 * 
		 * @param session
		 * @return
		 */
		Session getSession(Session session) {
			if(Instance.session == null)
			{
				Instance.session = new Session();
			}
			return Instance.session;
		}
		
		
}
