package alarmClock.model;

import com.sun.org.apache.regexp.internal.RE;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.*;

/**
 * Created by pascal on 5/8/17.
 */
public class ReminderList extends ObservableListBase {
    ObservableList reminders;
    private Stack<ObservableList> history;
    private Stack<ObservableList> undoneHisotry;

    public ReminderList() {
        //history = new Stack<ObservableList<Reminder>>;
        history = new Stack<ObservableList>();
        undoneHisotry = new Stack<ObservableList>();
        reminders = FXCollections.observableArrayList();
    }

    private  void pushState(){
        history.push(reminders);
    }
    private ObservableList popState(){
        if(history.peek() != null) {
            ObservableList rs = history.pop();
            undoneHisotry.push(rs);
            return rs;
        }
        else{
            return reminders;
        }
    }

    public void undo(){
        reminders = popState();
    }

    public void redo(){
        if(undoneHisotry.peek() != null) {
            ObservableList rs = undoneHisotry.pop();
            history.push(rs);
            reminders = rs;
        }
    }



    @Override
    public boolean addAll(Object[] elements) {
        boolean r = reminders.addAll(elements);
        pushState();
        return r;
    }

    @Override
    public boolean setAll(Object[] elements) {
        boolean r = reminders.setAll(elements);
        pushState();
        return r;
    }

    @Override
    public boolean setAll(Collection col) {
        boolean r = reminders.setAll(col);
        pushState();
        return r;
    }

    @Override
    public boolean removeAll(Object[] elements) {
        boolean r = reminders.removeAll(elements);
        pushState();
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
        reminders.remove(from,to);
        pushState();
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
        boolean r = reminders.addAll(index,c);
        pushState();
        return r;
    }

    @Override
    public void clear() {
        reminders.clear();
        pushState();
    }

    @Override
    public Object get(int index) {
        return reminders.get(index);
    }

    @Override
    public Object set(int index, Object element) {
        Object o=reminders.set(index,element);
        pushState();
        return o;
    }

    @Override
    public void add(int index, Object element) {
        reminders.addAll(index,element);
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
        return  reminders.indexOf(o);
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
        return reminders.subList(fromIndex,toIndex);
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
        return reminders.removeAll(c);
    }

    @Override
    public Object[] toArray(Object[] a) {
        return reminders.toArray();
    }

    /*
    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
    */
}
