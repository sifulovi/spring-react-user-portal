import React from 'react';
import './App.css';

import Error from "./components/Error";
import Login from "./components/Login";
import Registration from "./components/Registration";
import AuthActions from "./components/service/UserActions";
import Profile from "./components/Profile";
import {Redirect} from "react-router";
import {BrowserRouter, Switch, Route} from 'react-router-dom';

function App() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" component={Login} exact/>
                <Route path="/registration" component={Registration} exact/>

                {AuthActions.isAuthenticated() ?
                    <Route path="/profile" component={Profile} exact/> :
                    <Redirect to="/error"/>
                }
                <Route path="/error" component={Error} exact/>
            </Switch>
        </BrowserRouter>
    );
}

export default App;
