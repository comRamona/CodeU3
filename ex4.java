import java.util.Iterator;
import java.util.LinkedList;

class QueryStream {
	LinkedList<Query> queries;
	QIterator it;

	public QueryStream() {
		queries = new LinkedList<Query>();
	}

	public void add(Query q) {
		queries.add(q);
	}

	public QIterator iterator() {
		it = new QIterator(queries);
		return it;
	}
}

class Query {
	private long timestamp;
	private String s;

	public Query(String s, long timestamp) {
		this.s = s;
		this.timestamp = timestamp;
	}

	public String getString() {
		return s;
	}

	public long getTimestamp() {
		return timestamp;
	}
}

class QIterator {
	private LinkedList<Query> queries;
	private Iterator<Query> it;
	private Query current;
	private int index;
	private int len;
	private String[] words;
	private long currentTime, nextTime;

	public QIterator(LinkedList<Query> queries) {
		this.queries = queries;
		it = queries.iterator(); // use it to move to next queries
		index = -1;
		currentTime = 0;
		nextTime = 0;
	}

	public String[] words() {
		String st[] = current.getString().trim().split("\\W"); // get all words
																// from current
																// query
		return st;

	}

	public String next() {
		String w;
		if (index < 0) // need to move to next word
		{
			if (it.hasNext()) {
				boolean isFirst = false;
				if (current == null)
					isFirst = true;
				else
					currentTime = current.getTimestamp();
				current = it.next();
				words = words(); // get all words in current query
				len = words.length;
				index = 0; // prepare to later get first word
				nextTime = current.getTimestamp();
				String s = String.valueOf((double) (nextTime - currentTime));
				if (isFirst)
					return "0.0"; // first query, just 0
				return s; // replace "<NEWQUERY>" String
			} else
				return null; // no more words and no more queries

		}

		w = words[index]; // word at current position
		index++; // next word(if there is)
		if (index >= len) // finished all words in a String, start over
		{
			index = -1;
		}
		return w;

	}

	public boolean hasNext() {
		if (it.hasNext() == false && index < 0)
			// no more words in the current query and no more queries
			return false;
		return true;
	}

}

public class ex4 {

	public static void main(String[] args) {
		QueryStream qs = new QueryStream();
		Query q1 = new Query("I am a query", System.currentTimeMillis());
		Query q2 = new Query("   and a query I will be ...   ",
				System.currentTimeMillis() + 20);
		Query q3 = new Query("until the end of time!",
				System.currentTimeMillis() + 60); // 40 miliseconds distante to
													// first query
		qs.add(q1);
		qs.add(q2);
		qs.add(q3);
		QIterator it = new QIterator(qs.queries);
		while (it.hasNext())
			System.out.println(it.next());
	}

}

