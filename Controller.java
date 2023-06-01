package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.*;
import javax.swing.*;

import exceptions.*;

import buttons.*;
import engine.*;
import model.world.*;
import model.abilities.*;

import java.awt.*;

import views.*;

import panels.*;

public class Controller implements ActionListener, KeyListener{

	private GameStartView gameStartView;
	private WinnerView winnerView;
	private TurnOrderPanel turns;
	private PriorityQueue pq;
	private CurrChampPanel currChampPanel;
	private PlayerNameView playerNameView;
	private CurrChampPanel currChamp;
	private LeaderSelectView leaderSelectView;
	private JButton start;
	private ChampionsPanel chpanel;
	private LeadersPanel lpanel1;
	private LeadersPanel lpanel2;
	private Player p1;
	private Player p2;
	private JTextField tp1;
	private JTextField tp2;
	private String np1, np2;
	private ChampSelectView champSelectView;
	private ArrayList<Champion> availableChamps;
	private BoardView boardView;
	private int count1 = 0;
	private int count2 = 0;
	private ArrayList<String> chosenp1;
	private ArrayList<String> chosenp2;
	private Game game;
	private boolean flag;
	private Damageable[][] boardControl;
	
	public Controller() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		
		gameStartView = new GameStartView();
		playerNameView = new PlayerNameView();
		boardControl = new Damageable[5][5];
		champSelectView = new ChampSelectView();
		chpanel = champSelectView.getChamps();
		leaderSelectView = new LeaderSelectView();
		gameStartView.addKeyListener(this);
		playerNameView.addKeyListener(this);
		start = playerNameView.getDone();
		start.addActionListener(this);
		chosenp1 = new ArrayList<String>();
		chosenp2 = new ArrayList<String>();
		np1 = "";
		np2 = "";
		flag = false;
		p1 = new Player(np1);
		p2 = new Player(np2);
		game = new Game(p1, p2);
		pq = new PriorityQueue(6);
		game.loadAbilities("Abilities.csv");
		game.loadChampions("Champions.csv");
		availableChamps = game.getAvailableChampions();
		
		
		for(int i = 0; i<3; i++) {
			for(int j=0; j <5; j++) {
				chpanel.getChampButtons()[i][j].addActionListener(this);
			}
		}
		
		
		


		
	}
	
	
	public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
		new Controller();
			
	}


	@Override
	public void keyTyped(KeyEvent e) {

		
	}


	@Override
	public void keyPressed(KeyEvent e) {
				
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case(10):
			if(gameStartView.isVisible()) {
			gameStartView.setVisible(false);
			playerNameView.setVisible(true);}
			break;
			
			
		}		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==start) {
				playerNameView.setVisible(false);
				champSelectView.setVisible(true);
				tp1 = playerNameView.getTextp1();
				tp2 = playerNameView.getTextp2();
				np1 = tp1.getText();
				np2 = tp2.getText();
				p1 = new Player(np1);
				p2 = new Player(np2);
			}
			
			if(((JButton)e.getSource()).getActionCommand().equals("cast ability 1")) {	
				String q = ((BoardButton)e.getSource()).getInfo();
				String n = ((BoardButton)e.getSource()).getText();
				int ans = JOptionPane.showConfirmDialog(null, q + "\n Cast? ", n , JOptionPane.YES_NO_OPTION);
				if (ans ==0) {	
					
					Ability a = getAbility(n);
					if(a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					
						String[] responses = {"Up", "Down", "Left", "Right"};
						int direction = JOptionPane.showOptionDialog(null, "\n Select Direction.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
						if(direction==0) {
							try {
								game.castAbility(a, Direction.DOWN);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if(direction==1) {
							
							try {
								game.castAbility(a, Direction.UP);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if (direction==2) {
							
							try {
								game.castAbility(a, Direction.LEFT);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if(direction ==3) {
							
							try {
								game.castAbility(a, Direction.RIGHT);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						}
						
					}
					else if(a.getCastArea() == AreaOfEffect.SINGLETARGET) {

						String[] coordinates = {"0", "1", "2", "3", "4"};
						int x = JOptionPane.showOptionDialog(null, "\n Select X Coordinate.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, coordinates, 0);
						int y = JOptionPane.showOptionDialog(null, "\n Select Y Coordinate.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, coordinates, 0);
						
						try {
							game.castAbility(a, x, y);
						} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							String c = e1.getMessage();
							JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					} else {
						try {
							game.castAbility(a);
						} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							String c = e1.getMessage();
							JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
						} }
					
					}
				check();
				redraw();
				}
			
			if(((JButton)e.getSource()).getActionCommand().equals("cast ability 2")) {	
				String q = ((BoardButton)e.getSource()).getInfo();
				String n = ((BoardButton)e.getSource()).getText();
				int ans = JOptionPane.showConfirmDialog(null, q + "\n Cast? ", n , JOptionPane.YES_NO_OPTION);
				if (ans ==0) {	
					
					Ability a = getAbility(n);
					if(a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					
						String[] responses = {"Up", "Down", "Left", "Right"};
						int direction = JOptionPane.showOptionDialog(null, "\n Select Direction.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
						if(direction==0) {
							try {
								game.castAbility(a, Direction.DOWN);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if(direction==1) {
							
							try {
								game.castAbility(a, Direction.UP);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if (direction==2) {
							
							try {
								game.castAbility(a, Direction.LEFT);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if(direction ==3) {
							
							try {
								game.castAbility(a, Direction.RIGHT);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						}
						
					}
					else if(a.getCastArea() == AreaOfEffect.SINGLETARGET) {

						String[] coordinates = {"0", "1", "2", "3", "4"};
						int x = JOptionPane.showOptionDialog(null, "\n Select X Coordinate.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, coordinates, 0);
						int y = JOptionPane.showOptionDialog(null, "\n Select Y Coordinate.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, coordinates, 0);
						
						try {
							game.castAbility(a, x, y);
						} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							String c = e1.getMessage();
							JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					} else {
						try {
							game.castAbility(a);
						} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							String c = e1.getMessage();
							JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
						} }
					
					}
				check();
				redraw();
				}
			
			if(((JButton)e.getSource()).getActionCommand().equals("cast ability 3")) {	
				String q = ((BoardButton)e.getSource()).getInfo();
				String n = ((BoardButton)e.getSource()).getText();
				int ans = JOptionPane.showConfirmDialog(null, q + "\n Cast? ", n , JOptionPane.YES_NO_OPTION);
				if (ans ==0) {	
					
					Ability a = getAbility(n);
					if(a.getCastArea() == AreaOfEffect.DIRECTIONAL) {
					
						String[] responses = {"Up", "Down", "Left", "Right"};
						int direction = JOptionPane.showOptionDialog(null, "\n Select Direction.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
						if(direction==0) {
							try {
								game.castAbility(a, Direction.DOWN);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if(direction==1) {
							
							try {
								game.castAbility(a, Direction.UP);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if (direction==2) {
							
							try {
								game.castAbility(a, Direction.LEFT);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						} else if(direction ==3) {
							
							try {
								game.castAbility(a, Direction.RIGHT);
							} catch (NotEnoughResourcesException | AbilityUseException
									| CloneNotSupportedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								String c = e1.getMessage();
								JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
							}
							
						}
						
					}
					else if(a.getCastArea() == AreaOfEffect.SINGLETARGET) {

						String[] coordinates = {"0", "1", "2", "3", "4"};
						int x = JOptionPane.showOptionDialog(null, "\n Select X Coordinate.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, coordinates, 0);
						int y = JOptionPane.showOptionDialog(null, "\n Select Y Coordinate.", q, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, coordinates, 0);
						
						try {
							game.castAbility(a, x, y);
						} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
								| CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							String c = e1.getMessage();
							JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
						}
						
					} else {
						try {
							game.castAbility(a);
						} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							String c = e1.getMessage();
							JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
						} }
					
					}
				check();
				redraw();
				}
			
			if(((JButton)e.getSource()).getActionCommand().equals("attack")) {	
				String[] responses = {"Up", "Down", "Left", "Right"};
				int direction = JOptionPane.showOptionDialog(null, "\n Select Direction.", "Attack", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
				if(direction==0) {

					try {
						game.attack(Direction.DOWN);
					} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				}else if(direction==1) {
					try {
						game.attack(Direction.UP);
					} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				}else if(direction==2) {
					try {
						game.attack(Direction.LEFT);
					} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				}else if(direction==3) {
					
					try {
						game.attack(Direction.RIGHT);
					} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				check();
				redraw();
			}
			
			if(((JButton)e.getSource()).getActionCommand().equals("move")) {	
				String[] responses = {"Up", "Down", "Left", "Right"};
				int direction = JOptionPane.showOptionDialog(null, "\n Select Direction.", "Move", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responses, 0);
				if(direction==0) {
					try {
						game.move(Direction.DOWN);
					} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else if(direction==1) {
					try {
						game.move(Direction.UP);
					} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}

				}else if(direction==2) {
					try {
						game.move(Direction.LEFT);
					} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}

				}else if(direction==3) {
					try {
						game.move(Direction.RIGHT);
					} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						String c = e1.getMessage();
						JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				check();
				redraw();
			}
			
			if(((JButton)e.getSource()).getActionCommand().equals("end turn")) {	
				game.endTurn();
				check();
				turns.update();
				redraw();
			}
			
			if(((JButton)e.getSource()).getActionCommand().equals("use leader ability")) {	
				
				try {
					game.useLeaderAbility();
				} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					String c = e1.getMessage();
					JOptionPane.showMessageDialog(boardView, c, "Error", JOptionPane.ERROR_MESSAGE);
				}
				check();
				redraw();
				}
			
			
			if(((JButton)e.getSource()).getActionCommand().equals("currChamp")) {	
				String q = ((BoardButton)e.getSource()).getInfo();
				JOptionPane.showMessageDialog(boardView,q);
			}
			
			if(((JButton)e.getSource()).getActionCommand().equals(p1.getName())) {	
				String q = ((BoardButton)e.getSource()).getInfo();
				JOptionPane.showMessageDialog(boardView,q);
			}
			
			if(((JButton)e.getSource()).getActionCommand().equals(p2.getName())) {	
				String q = ((BoardButton)e.getSource()).getInfo();
				JOptionPane.showMessageDialog(boardView,q);
			}
			

			if(((JButton)e.getSource()).getActionCommand().equals("Captain America")) {				
				
				
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					
					
				}
				
				
			}
			

			if(((JButton)e.getSource()).getActionCommand().equals("Deadpool")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Dr Strange")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Electro")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Ghost Rider")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}}

			if(((JButton)e.getSource()).getActionCommand().equals("Hela")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Hulk")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Iceman")) {
				
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Ironman")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Loki")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Quicksilver")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Spiderman")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
			}
			}
			
			if(((JButton)e.getSource()).getActionCommand().equals("Thor")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}}

			
			if(((JButton)e.getSource()).getActionCommand().equals("Venom")) {
				if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
					String q = ((ChampButton)e.getSource()).getInfo();
					
					String h = ((ChampButton)e.getSource()).getActionCommand();

					 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						((ChampButton)e.getSource()).setEnabled(false);
						
						if(count1<3) {
							chosenp1.add(h);
							p1.getTeam().add(getChamp(h));	

						}
						else {
							chosenp2.add(h);
							p2.getTeam().add(getChamp(h));	
						}
						count1++;	
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					
					String h = ((ChampButton)e.getSource()).getActionCommand();
					 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
					if (ans ==0) {
						
						if(count2<1){
							p1.setLeader(getChamp(h));
							lpanel1.setVisible(false);
							leaderSelectView.add(lpanel2, BorderLayout.SOUTH);

					
						}
						else{ 
							p2.setLeader(getChamp(h));
							leaderSelectView.setVisible(false);
						}
						count2++;
					}
					try {
						onClose();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}}

			
				if(((JButton)e.getSource()).getActionCommand().equals("Yellow Jacket")) {
					if(p1.getTeam().size()<3 || p2.getTeam().size()<3) {
						String q = ((ChampButton)e.getSource()).getInfo();
						
						String h = ((ChampButton)e.getSource()).getActionCommand();

						 int ans = JOptionPane.showConfirmDialog(null, q + "\n Add to Team? ", "Select Team" , JOptionPane.YES_NO_OPTION);
						if (ans ==0) {
							((ChampButton)e.getSource()).setEnabled(false);
							
							if(count1<3) {
								chosenp1.add(h);
								p1.getTeam().add(getChamp(h));	

							}
							else {
								chosenp2.add(h);
								p2.getTeam().add(getChamp(h));	
							}
							count1++;	
						}
						try {
							onClose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						
						String h = ((ChampButton)e.getSource()).getActionCommand();
						 int ans = JOptionPane.showConfirmDialog(null, h + "\n Select as Leader? ", "Select Leader" , JOptionPane.YES_NO_OPTION);
						if (ans ==0) {
							
							if(count2<1){
								p1.setLeader(getChamp(h));
								lpanel1.setVisible(false);
								lpanel2.setVisible(true);
						
							}
							else{ 
								p2.setLeader(getChamp(h));
								leaderSelectView.setVisible(false);
							}
							count2++;
						}
						try {
							onClose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
					
				}
			
			
			
	}
	
	public void onClose() throws IOException {
		
		if(count1 >=6 && flag==false) {
			flag=true;
			champSelectView.setVisible(false);
			lpanel1 = new LeadersPanel(chosenp1);
			lpanel2 = new LeadersPanel(chosenp2);
			leaderSelectView.add(lpanel2, BorderLayout.SOUTH);
			leaderSelectView.add(lpanel1, BorderLayout.SOUTH);
			game = new Game(p1, p2);
			
			for(Champion r: p1.getTeam()) {
				pq.insert(r);
			}
			for(Champion r: p2.getTeam()) {
				pq.insert(r);
			}
			
			turns = new TurnOrderPanel(pq);
			leaderSelectView.setVisible(true);
			
				for(int j=0; j <3; j++) {
					lpanel1.getLeaderButtons()[0][j].addActionListener(this);
					lpanel2.getLeaderButtons()[0][j].addActionListener(this);

				}
			
				

		}
		
		if(count2>=2) {
			boardView = new BoardView(p1, p2);
			boardView.addKeyListener(this);
			boardView.add(turns, BorderLayout.WEST);
			boardView.getPp1().getStats().addActionListener(this);
			boardView.getPp2().getStats().addActionListener(this);
			currChampPanel = new CurrChampPanel(game.getCurrentChampion());
			currChampPanel.getCurrChampion().addActionListener(this);
			
			for(int i =0; i<3; i++) {
				for (int j=0;j<2; j++) {
					currChampPanel.getCast().getCastButtons()[i][j].addActionListener(this);
				}
			}
			
			currChampPanel.getEndTurn().addActionListener(this);
			
			boardView.add(currChampPanel, BorderLayout.SOUTH);
			//boardView.add(turns, BorderLayout.WEST);

			
			redraw();
			boardView.setVisible(true);


			
		}
		
			
	}
	
	
	public Champion getChamp(String s) {
		
		for(Champion c : availableChamps) {
			
			if(c.getName().equals(s))
				return c;	
		}
		return null;
	}
	
	public Ability getAbility(String s) {
		
		for(Ability a : game.getCurrentChampion().getAbilities()) {
			
			if(a.getName().equals(s))
				return a;	
		}
		return null;
	}
	
	
	public void redraw() {
		
		
		String f = p1.toString() + "\n Leader Ability Used: " + game.isFirstLeaderAbilityUsed();
		String d = p2.toString() + "\n Leader Ability Used: " + game.isSecondLeaderAbilityUsed();
		boardView.getPp1().getStats().setInfo(f);
		boardView.getPp2().getStats().setInfo(d);
		
		Champion c = game.getCurrentChampion();
		ArrayList<Ability> a = c.getAbilities();
		for(int i = 0; i< a.size(); i++) {
			int g =i+1;
			currChampPanel.getCast().getCastButtons()[i][0].setText(a.get(i).getName());
			currChampPanel.getCast().getCastButtons()[i][0].setInfo(a.get(i).toString());
			currChampPanel.getCast().getCastButtons()[i][0].setActionCommand("cast ability " + g);
		}
		
		currChampPanel.getCurrChampion().setText(c.getName());
		currChampPanel.getCurrChampion().setInfo(c.toStringCurr());
		int k = c.getCurrentHP();
		int m = c.getMaxHP();
		currChampPanel.getCurrHP().setMaximum(m);
		currChampPanel.getCurrHP().setPreferredSize(new Dimension(450,30));
		currChampPanel.getCurrHP().setValue(k);
		currChampPanel.getCurrHP().setString(k + "/" + m + " HP");

		
		for(int h = 0; h< 5; h++) {	
			for(int g = 0; g< 5; g++) {
				boardView.getBoard().getBoardButtons()[h][g].addActionListener(this);
				if(game.getBoard()[h][g]!=null) {
					boardControl[h][g] = (Damageable) game.getBoard()[h][g];
					if(boardControl[h][g] instanceof Champion) {
						
						if(p1.getTeam().contains(((Champion) boardControl[h][g]))) 
							boardView.getBoard().getBoardButtons()[h][g].setBackground(Color.red);
						if(p2.getTeam().contains(((Champion) boardControl[h][g]))) 
							boardView.getBoard().getBoardButtons()[h][g].setBackground(Color.blue);
						if(game.getCurrentChampion().equals(((Champion) boardControl[h][g]))) 
							boardView.getBoard().getBoardButtons()[h][g].setBackground(Color.green);
						
						boardView.getBoard().getBoardButtons()[h][g].setText(((Champion) boardControl[h][g]).getName());
					}else 
						boardView.getBoard().getBoardButtons()[h][g].setText(boardControl[h][g].toString());
				}
				else {
					boardView.getBoard().getBoardButtons()[h][g].setText("(" + h +"," + g + ")");
					boardView.getBoard().getBoardButtons()[h][g].setBackground(null);
					//game.getBoard()[h][g] = null;
					
				}

			}
		}
		
	}

	
	public void check() {
		if(game.checkGameOver()!=null) {
			boardView.setVisible(false);
			winnerView = new WinnerView(game.checkGameOver().getName());
			winnerView.setVisible(true);
		}
	}

	
}
