package github.yeori.dtogen.level1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import github.yeori.dtommic.DtoMimic;

class WithListField {

	DtoMimic gen = new DtoMimic();
	
	@Test
	void test() {
		User jack = new User();
		jack.setName("JACK");
		jack.setOrders(new ArrayList<>(List.of(
			new Order(12L, LocalDateTime.now()),
			new Order(14L, LocalDateTime.now())
		)));
		
		User copy = gen.mimic(jack);
		assertTrue (jack != copy);
		assertTrue(jack.getName() == copy.getName());
		
		assertTrue (jack.getOrders() != copy.getOrders());
		assertEquals(jack.getOrders(), copy.getOrders());
		
	}
	
	
	public static class Order {
		private long orderSeq;
		private LocalDateTime orderTime;
		
		public Order() {
		}
		public Order(long seq, LocalDateTime time) {
			this.orderSeq = seq;
			this.orderTime = time;
		}
		public long getOrderSeq() {
			return orderSeq;
		}
		public void setOrderSeq(long orderSeq) {
			this.orderSeq = orderSeq;
		}
		public LocalDateTime getOrderTime() {
			return orderTime;
		}
		public void setOrderTime(LocalDateTime orderTime) {
			this.orderTime = orderTime;
		}
		@Override
		public String toString() {
			return "Order [orderSeq=" + orderSeq + ", orderTime=" + orderTime + "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (orderSeq ^ (orderSeq >>> 32));
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Order other = (Order) obj;
			if (orderSeq != other.orderSeq)
				return false;
			return true;
		}
		
		
	}
	public static class User {
		private String name;
		private List<Order> orders;
		public User(){}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<Order> getOrders() {
			return orders;
		}
		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}
		@Override
		public String toString() {
			return "User [name=" + name + ", orders=" + orders + "]";
		}
		
	}

}
