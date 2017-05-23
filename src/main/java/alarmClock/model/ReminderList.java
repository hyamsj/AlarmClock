package alarmClock.model;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.*;

/**
 * Created by pascal on 5/8/17.
 */
public class ReminderList implements ObservableList {
    ObservableList reminders;
    private Stack<ObservableList> history;
    private Stack<ObservableList> undoneHistory;

    public ReminderList() {
        history = new Stack<>();
        undoneHistory = new Stack<>();
        reminders = FXCollections.observableArrayList();
    }

    /*
    public ReminderList(ObservableList<Reminder> r) {
        this.reminders = r;
    }
    */
    public ReminderList(ObservableList<Reminder> r) {
        // makes reminders not removable reminders = FXCollections.observableArrayList();
        this.reminders = r;
        history = new Stack<>();
        undoneHistory = new Stack<>();
    }

    private void pushState() {
        ObservableList<Reminder> old = FXCollections.observableArrayList();
        old = reminders;
        /*
        for (Object o :reminders){
            if (o instanceof  Reminder){
                Reminder r = (Reminder) o;
                old.add(r);
            }
        }
        */
        history.push(old);

    }

    private ObservableList popState() {
        ObservableList<Reminder> newReminder= FXCollections.observableArrayList();
        if (history.peek() != null) {
            ObservableList rs = history.pop();
            undoneHistory.push(rs);
            /*
            System.out.print(reminders);
            for (Object o :rs){
                if (o instanceof  Reminder){
                    Reminder r = (Reminder) o;
                    newReminder.add(r);
                }
            }
            */
            return newReminder;
        } else {
            return reminders;
        }
    }

    public void undo() {
            /*
        System.out.println("Reminder.undo() is called");
        reminders = popState();
        System.out.println(r.toString());
        for(Reminder r = reminders)
            System.out.println(r.toString());
            */
    }

    public void redo() {
        if (undoneHistory.peek() != null) {
            //ObservableList rs = undoneHistory.pop();
            ObservableList rs = popState();
            //history.push(rs);
            this.reminders = rs;
        }
    }


    @Override
    public void addListener(ListChangeListener listener) {
        reminders.addListener(listener);
    }

    @Override
    public void removeListener(ListChangeListener listener) {
        reminders.removeListener(listener);
    }

    @Override
    public boolean addAll(Object[] elements) {
        pushState();
        boolean r = reminders.addAll(elements);
        return r;
    }

    @Override
    public boolean setAll(Object[] elements) {
        pushState();
        boolean r = reminders.setAll(elements);
        return r;
    }

    @Override
    public boolean setAll(Collection col) {
        pushState();
        boolean r = reminders.setAll(col);
        return r;
    }

    @Override
    public boolean removeAll(Object[] elements) {
        pushState();
        boolean r = reminders.removeAll(elements);
        return r;
    }

    @Override
    public boolean retainAll(Object[] elements) {
        boolean r = reminders.retainAll(elements);
        pushState();
        return r;
    }

    @Override
    public void remove(int from, int to) {
        pushState();
        reminders.remove(from, to);
    }


    @Override
    public boolean isEmpty() {
        return reminders.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return reminders.contains(o);
    }

    @Override
    public Iterator iterator() {
        return reminders.iterator();
    }

    @Override
    public int size() {
        return reminders.size();
    }

    @Override
    public Object[] toArray() {
        return reminders.toArray();
    }


    @Override
    public boolean add(Object o) {
        boolean r = reminders.add(o);
        pushState();
        return r;
    }

    @Override
    public boolean remove(Object o) {
        boolean r = reminders.remove(o);
        pushState();
        return r;
    }


    @Override
    public boolean addAll(Collection c) {
        boolean r = reminders.addAll(c);
        pushState();
        return r;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        boolean r = reminders.addAll(index, c);
        pushState();
        return r;
    }

    @Override
    public void clear() {
        reminders.clear();
        pushState();
    }

    public ArrayList<Reminder> getSerializable() {
        //TODO solve type conversation on a better place
        ArrayList<Reminder> output = new ArrayList<>();
        for (Object r : reminders) {
            r = r;
            output.add((Reminder) r);
        }
        return output;
    }

    @Override
    public Object get(int index) {
        return reminders.get(index);
    }

    @Override
    public Object set(int index, Object element) {
        Object o = reminders.set(index, element);
        pushState();
        return o;
    }

    @Override
    public void add(int index, Object element) {
        reminders.addAll(index, element);
        pushState();
    }

    @Override
    public Object remove(int index) {
        Object o = reminders.remove(index);
        pushState();
        return o;
    }

    @Override
    public int indexOf(Object o) {
        return reminders.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return reminders.lastIndexOf(o);
    }

    @Override
    public ListIterator listIterator() {
        return reminders.listIterator();
    }

    @Override
    public ListIterator listIterator(int index) {
        return reminders.listIterator(index);
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return reminders.subList(fromIndex, toIndex);
    }

    @Override
    public boolean retainAll(Collection c) {
        boolean b = reminders.retainAll(c);
        pushState();
        return b;
    }

    @Override
    public boolean removeAll(Collection c) {
        boolean b = reminders.removeAll(c);
        pushState();
        return b;
    }

    @Override
    public boolean containsAll(Collection c) {
        return reminders.containsAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return reminders.toArray();
    }

    //TODO refactor until EOF
    public void addOwnListener(InvalidationListener listener) {
        reminders.addListener(listener);
    }

    public void addOwnListener(ListChangeListener listener) {
        reminders.addListener(listener);
    }


    public void removeOwnListener(InvalidationListener listener) {
        reminders.removeListener(listener);
    }

    public void removeOwnListener(ListChangeListener listener) {
        reminders.removeListener(listener);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        reminders.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        reminders.addListener(listener);
    }
}
