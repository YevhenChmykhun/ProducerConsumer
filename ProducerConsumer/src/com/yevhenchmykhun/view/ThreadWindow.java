package com.yevhenchmykhun.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.yevhenchmykhun.model.AbstractWorker;
import com.yevhenchmykhun.model.Buffer;
import com.yevhenchmykhun.model.Consumer;
import com.yevhenchmykhun.model.IntegerGenerator;
import com.yevhenchmykhun.model.Producer;
import com.yevhenchmykhun.model.ProducerConsumer;
import com.yevhenchmykhun.model.Range;
import com.yevhenchmykhun.observer.Observer;

@SuppressWarnings("serial")
public class ThreadWindow extends JFrame implements Observer {

	Producer producer;
	ProducerConsumer producerConsumer;
	AbstractWorker consumer;
	JTextArea randomValueArea = new JTextArea();
	JTextArea squaredArea = new JTextArea();
	JTextArea consoleArea = new JTextArea();

	public ThreadWindow(String name) {
		super(name);

		Buffer<Integer> queueRandomValues = new Buffer<Integer>();
		Buffer<Integer> queueSquares = new Buffer<Integer>();
		IntegerGenerator generator = new IntegerGenerator(new Range(0, 10));

		JPanel randomValuePanel = new JPanel(new GridLayout(1, 1));
		randomValuePanel.setBorder(BorderFactory
				.createTitledBorder("Random Number"));
		JScrollPane randomNumberScrollPane = new JScrollPane(randomValueArea);
		randomValuePanel.add(randomNumberScrollPane);

		JPanel squaredPanel = new JPanel(new GridLayout(1, 1));
		squaredPanel.setBorder(BorderFactory.createTitledBorder("Squared"));
		squaredPanel.add(new JScrollPane(squaredArea));

		JPanel consolePanel = new JPanel(new GridLayout(1, 1));
		consolePanel.setBorder(BorderFactory.createTitledBorder("Console"));
		consolePanel.add(new JScrollPane(consoleArea));

		JPanel textsArea = new JPanel(new GridLayout(1, 3));
		textsArea.add(randomValuePanel);
		textsArea.add(squaredPanel);
		textsArea.add(consolePanel);

		add(textsArea, "Center");

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 3));
		buttonsPanel.setBorder(BorderFactory.createTitledBorder("Action"));

		JButton randomValueStartButton = new JButton("Start");
		JButton randomValueStopButton = new JButton("Stop");
		JButton squaredStartButton = new JButton("Start");
		JButton squaredStopButton = new JButton("Stop");
		JButton consoleStartButton = new JButton("Start");
		JButton consoleStopButton = new JButton("Stop");

		randomValueStartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (producer == null) {
					producer = new Producer(100, generator, queueRandomValues);
					producer.addObserver(ThreadWindow.this);
				} else {
					producer.resume();
				}
				randomValueStartButton.setEnabled(false);
				randomValueStopButton.setEnabled(true);
			}
		});

		randomValueStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				producer.suspend();
				randomValueStartButton.setEnabled(true);
				randomValueStopButton.setEnabled(false);
			}
		});

		squaredStartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (producerConsumer == null) {
					producerConsumer = new ProducerConsumer(1000,
							queueRandomValues, queueSquares);
					producerConsumer.addObserver(ThreadWindow.this);
				} else {
					producerConsumer.resume();
				}
				squaredStartButton.setEnabled(false);
				squaredStopButton.setEnabled(true);
			}
		});

		squaredStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				producerConsumer.suspend();
				squaredStartButton.setEnabled(true);
				squaredStopButton.setEnabled(false);
			}
		});

		consoleStartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (consumer == null) {
					consumer = new Consumer(2000, queueSquares);
					consumer.addObserver(ThreadWindow.this);
				} else {
					consumer.resume();
				}
				consoleStartButton.setEnabled(false);
				consoleStopButton.setEnabled(true);
			}
		});

		consoleStopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				consumer.suspend();
				consoleStartButton.setEnabled(true);
				consoleStopButton.setEnabled(false);
			}
		});

		buttonsPanel.add(randomValueStartButton);
		buttonsPanel.add(squaredStartButton);
		buttonsPanel.add(consoleStartButton);
		buttonsPanel.add(randomValueStopButton);
		buttonsPanel.add(squaredStopButton);
		buttonsPanel.add(consoleStopButton);

		add(buttonsPanel, "South");

	}

	@Override
	public void updateProducerTextArea(int value) {
		randomValueArea.append(Integer.toString(value) + "\n");
	}

	@Override
	public void updateProducerConsumerTextArea(int value) {
		squaredArea.append(Integer.toString(value) + "\n");
	}

	@Override
	public void updateConsumerTextArea(int value) {
		consoleArea.append(Integer.toString(value) + "\n");
	}

}
