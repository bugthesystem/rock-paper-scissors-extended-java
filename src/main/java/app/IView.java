package app;

import lib.models.MatchResult;

import java.util.ArrayList;

public interface IView {
    public void printResults(ArrayList<MatchResult> results);
}
