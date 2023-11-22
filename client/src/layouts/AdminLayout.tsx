import * as React from 'react';
import Header from "../components/Header/Header";
import AdminDrawer from "../components/AdminDrawer/AdminDrawer";
import { styled } from "@mui/material/styles";

const drawerWidth = 240;

const Main = styled('main', { shouldForwardProp: (prop) => prop !== 'open' })<{
    open?: boolean;
  }>(({ theme, open }) => ({
    flexGrow: 1,
    padding: theme.spacing(3),
    transition: theme.transitions.create('margin', {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.leavingScreen,
    }),
    marginLeft: 0,
    ...(open && {
      transition: theme.transitions.create('margin', {
        easing: theme.transitions.easing.easeOut,
        duration: theme.transitions.duration.enteringScreen,
      }),
      marginLeft: `+${drawerWidth}px`,
    }),
}));

const AdminLayout = ({ children }) => {
    const [openDrawer, setOpenDrawer] = React.useState(true);

    const handleDrawerOpen = () => {
        setOpenDrawer(true);
    };

    const handleDrawerClose = () => {
        setOpenDrawer(false);
    };

    return (
        <div>
            <Header openDrawer={openDrawer} handleDrawerOpen={handleDrawerOpen} drawerWidth={drawerWidth} />
            <AdminDrawer openDrawer={openDrawer} handleDrawerClose={handleDrawerClose} drawerWidth={drawerWidth} />
            <Main open={openDrawer}>{ children }</Main>
        </div>
    );
}

export default AdminLayout;