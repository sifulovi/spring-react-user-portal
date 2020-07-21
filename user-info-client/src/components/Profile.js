import React, {Component} from 'react';
import Header from "./Header";
import CardView from "./CardView";

class Profile extends Component {
    render() {
        return (
            <div>
                <Header/>
                <br/>
                <br/>
               <CardView/>
            </div>
        );
    }
}

export default Profile;
