package bagstatckqueue;
/*环形缓冲区。环形缓冲区，又称为环形队列，是一种定长为 N 的先进先出的数据结构。
 * 它在进程间的异步数据传输或记录日志文件时十分有用。当缓冲区为空时，
 * 消费者会在数据存入缓冲区前等待；当缓冲区满时，生产者会等待数据存入缓冲区。
 * 为 RingBuffer 设计一份 API 并用（回环）数组将其实现。
 */
public class RingBuffer<Item> {
	private Item[] a;
	private int N;
	private int head=0;
	private int tail=-1;
	public RingBuffer(int n){
		a=(Item[])new Object[n];
		N=0;
	}
	private void resize(int n){
//		Item[] temp
	}

}
