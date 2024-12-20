import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import NotFound from './pages/NotFound';

const App: React.FC = () => (
  <Router>
    <Switch>
      <Route exact path="/" component={Dashboard} />
      <Route component={NotFound} />
    </Switch>
  </Router>
);

export default App;
