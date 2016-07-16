package com.bmpl.ims.users.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.bmpl.ims.users.dao.CoursesDAO;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class UpdateCourseView extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	JTextArea textUpdateArea;
	JLabel lblCou;
	DefaultListModel<String> listModel;

	public static void main(String[] args) {

		UpdateCourseView frame = new UpdateCourseView();
		frame.setVisible(true);

	}

	public UpdateCourseView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 350);
		contentPane = new JPanel();

		contentPane.setLayout(null);
		setContentPane(contentPane);

		listModel = new DefaultListModel<String>();
		JList<String> courseList = new JList<String>(listModel);
		courseList.setValueIsAdjusting(true);
		courseList.setBounds(22, 57, 138, 177);

		JButton btnCourses = new JButton("View Courses");
		btnCourses.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bringCourses();

			}
		});
		btnCourses.setBounds(31, 23, 140, 15);
		contentPane.add(btnCourses);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 50, 150, 250);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(courseList);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String item = courseList.getSelectedValue();
				System.out.println("item is :" + item);
				update(item);

			}
		});
		btnNewButton.setBounds(229, 275, 117, 25);
		contentPane.add(btnNewButton);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					System.out.println("No button clicked");
				} else if (response == JOptionPane.YES_OPTION) {
					System.out.println("Yes button clicked");
					String item = courseList.getSelectedValue();
					deleteItem(item);
				} else if (response == JOptionPane.CLOSED_OPTION) {
					System.out.println("JOptionPane closed");
				}

			}
		});
		btnDelete.setBounds(397, 275, 117, 25);
		contentPane.add(btnDelete);

		lblCou = new JLabel("Course Selected :");
		lblCou.setBounds(276, 27, 238, 40);
		contentPane.add(lblCou);
		lblCou.setVisible(false);

		textUpdateArea = new JTextArea();
		textUpdateArea.setBounds(276, 81, 238, 160);
		contentPane.add(textUpdateArea);

		textUpdateArea.setVisible(false);

	}

	protected void update(String item) {

		CoursesDAO coursesDAO = new CoursesDAO();
		CourseView cv = null;
		try {

			textUpdateArea.setText(coursesDAO.updateCourse(item));
			lblCou.setVisible(true);
			textUpdateArea.setVisible(true);

			System.out.println(coursesDAO.showCourse(item));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cant update..");
		}

	}

	protected void deleteItem(String item) {
		CoursesDAO coursesDAO = new CoursesDAO();
		System.out.println(item);
		try {
			if (item != null) {

				if (CoursesDAO.deleteCourse(item)) {
					String info = coursesDAO.updateCourse(item);
					textUpdateArea.setText(info);
					textUpdateArea.setVisible(true);
					lblCou.setVisible(true);
					JOptionPane.showMessageDialog(this, "Course Deleted " + item);
					System.out.println("course deleted :" + item);

				}
			} else {
				JOptionPane.showMessageDialog(this, "No course to delete");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void bringCourses() {

		CoursesDAO coursesDAO = new CoursesDAO();
		try {
			ArrayList<String> list = coursesDAO.getCourse();
			if (!list.isEmpty()) {
				for (String name : list) {
					listModel.addElement(name);
					System.out.println(name);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}
}
