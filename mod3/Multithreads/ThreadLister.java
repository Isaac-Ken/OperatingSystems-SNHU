/**
 * List all thread groups and threads in each group in the JVM.
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts with Java - Eighth Edition
 * Copyright John Wiley & Sons - 2010.
 */

public class ThreadLister
{
	ThreadGroup rootThreadGroup = null;
 
	/* Comment Here */
	ThreadGroup getRootThreadGroup( ) {
    		if ( rootThreadGroup != null )
        		return rootThreadGroup;
    		ThreadGroup currentThreadGroup = Thread.currentThread( ).getThreadGroup( );
    		ThreadGroup parentThreadGroup;
    		while ( (parentThreadGroup = currentThreadGroup.getParent( )) != null )
        		currentThreadGroup = parentThreadGroup;

		rootThreadGroup = currentThreadGroup;

    		return rootThreadGroup;
	}

	/* Comment Here */
	ThreadGroup[] getAllThreadGroups( ) {
    		final ThreadGroup root = getRootThreadGroup( );

		/* Comment Here */
   		int alloc = root.activeGroupCount( );

		/* Comment Here */
    		int n = 0;
    		ThreadGroup[] groups;
    		do {
			/* Comment Here */
        		alloc *= 2;
        		groups = new ThreadGroup[alloc];

			/* Comment Here */
        		n = root.enumerate(groups, true);
    		} while (n == alloc);

		   /* Comment Here */ 
    		ThreadGroup[] allGroups = new ThreadGroup[n+1];

		   /* Comment Here */
    		allGroups[0] = root;

		   /* Comment Here */
    		System.arraycopy( groups, 0, allGroups, 1, n );

    		return allGroups;
	}

	public static void main(String[] args) {
		
		new CreateThreadGroups();
		
		ThreadLister groups = new ThreadLister();

		/* Put thread groups into list */
		ThreadGroup[] groupList = groups.getAllThreadGroups();

		/* Comment Here */
		for (int i = 0; i < groupList.length; i++) {
			/* Make a new thread group list */
			Thread list[] = new Thread[groupList[i].activeCount() * 2];
			groupList[i].enumerate(list, false);
		
			/* output length of list  */
			System.out.println(groupList[i].getName());
			for (int j = 0; j < list.length; j++) {
				if (list[j] != null)
					System.out.println("\t"+list[j].getName()+":"+list[j].getId()+":"+list[j].getState()+":"+list[j].isDaemon());
			}
		}
	}
}
