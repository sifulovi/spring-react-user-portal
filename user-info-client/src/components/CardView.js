import React, {Component} from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Typography from '@material-ui/core/Typography';
import Grid from "@material-ui/core/Grid";
import {withStyles} from "@material-ui/core";
import UserActions from "./service/UserActions";

const useStyles = (theme) => ({
    root: {
        marginTop: theme.spacing(1),
        marginBottom: theme.spacing(1),
        padding: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
            marginTop: theme.spacing(1),
            marginBottom: theme.spacing(3),
            padding: theme.spacing(3),
        },
    },
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
    layout: {
        width: 'auto',
        marginLeft: theme.spacing(2),
        marginRight: theme.spacing(2),
        [theme.breakpoints.up(600 + theme.spacing(2) * 2)]: {
            width: 600,
            marginLeft: 'auto',
            marginRight: 'auto',
        },
    }
});


class CardView extends Component {
    constructor(props) {
        super(props);

        this.state = {
            user: {
                fullName: '',
                email: ''
            }
        }
    }

    componentDidMount() {
        UserActions.findUserDetails()
            .then(response => {
                console.log(response)
                this.setState({
                    user: response.data
                })
            })
            .catch(err => {
                console.log(err)
            })
    }

    render() {
        const {classes} = this.props;
        return (
            <div>
                <main className={classes.layout}>
                    <Card className={classes.root}>
                        <CardContent>

                            <Typography variant="h5" component="h2">
                                {this.state.user.fullName}
                            </Typography>
                            <Typography className={classes.pos} color="textSecondary">
                                {this.state.user.email}
                            </Typography>
                        </CardContent>
                    </Card>
                </main>
            </div>
        );
    }
}

export default withStyles(useStyles)(CardView);
