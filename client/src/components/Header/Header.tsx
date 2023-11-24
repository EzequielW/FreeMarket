import * as React from 'react';
import { Box, Toolbar, IconButton, Typography, Menu,
    Container, Avatar, Button, Tooltip, MenuItem, AppBar, useTheme } from '@mui/material';

import { deepOrange } from '@mui/material/colors';

import MenuIcon from '@mui/icons-material/Menu';
import AdbIcon from '@mui/icons-material/Adb';

import { Link, useNavigate } from 'react-router-dom';

import authService from '../../services/authService';

const pages = [
    {
        name: 'Products',
        link: '/'
    },
    {
        name: 'orders',
        link: '/orders'
    }
];

const settings = [
    {
        name: 'Logout',
        link: '/login',
        onClick: authService.logout
    }
];

const dashboardButtonStyle = { 
    mx: 2, 
    color: 'white', 
    borderColor: 'white', 
    ":hover": { 
        borderColor: 'white' 
    } 
}

interface HeaderProps {
    openDrawer?: boolean,
    handleDrawerOpen?: () => void,
    drawerWidth?: number
}

const Header = (headerProps: HeaderProps) => {
    const navigate = useNavigate();
    const theme = useTheme();
    
    const [anchorElNav, setAnchorElNav] = React.useState(null);
    const [anchorElUser, setAnchorElUser] = React.useState<null | HTMLElement>(null);
    const settingsOpen = Boolean(anchorElUser);

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };
    const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
        console.log("anchor", event.currentTarget);
        setAnchorElUser(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const handleCloseUserMenu = () => {
        setAnchorElUser(null);
    };

    // Styling
    const appBarStyle = {
        width: `calc(100% - ${headerProps.openDrawer ? headerProps.drawerWidth : 0}px)`,
        marginLeft: `${headerProps.openDrawer ? headerProps.drawerWidth : 0}px`,
        transition: theme.transitions.create(['margin', 'width'], {
            easing: headerProps.openDrawer ? theme.transitions.easing.easeOut : theme.transitions.easing.sharp,
            duration: headerProps.openDrawer ? theme.transitions.duration.enteringScreen : theme.transitions.duration.leavingScreen,
        })
    }

    return (
        <Box sx={{ display: 'flex' }}>
            <AppBar position="static" style={appBarStyle}>
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        {
                            headerProps.handleDrawerOpen &&
                            (
                                <IconButton
                                    color="inherit"
                                    aria-label="open drawer"
                                    onClick={headerProps.handleDrawerOpen}
                                    edge="start"
                                    sx={{ mr: 2, ...(headerProps.openDrawer && { display: 'none' }) }}
                                >
                                    <MenuIcon />
                                </IconButton>
                            )
                        }
                        <AdbIcon sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
                        <Typography
                            variant="h6"
                            noWrap
                            component="a"
                            href="/"
                            sx={{
                            mr: 2,
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            color: 'inherit',
                            textDecoration: 'none',
                            }}
                        >
                            FreeMarket
                        </Typography>

                        <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
                            <IconButton
                            size="large"
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleOpenNavMenu}
                            color="inherit"
                            >
                                <MenuIcon />
                            </IconButton>
                            <Menu
                            id="menu-appbar"
                            anchorEl={anchorElNav}
                            anchorOrigin={{
                                vertical: 'bottom',
                                horizontal: 'left',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'left',
                            }}
                            open={Boolean(anchorElNav)}
                            onClose={handleCloseNavMenu}
                            sx={{
                                display: { xs: 'block', md: 'none' },
                            }}
                            >
                                {pages.map((page) => (
                                    <MenuItem key={page.name} onClick={handleCloseNavMenu} component={Link} to={page.link}>
                                        <Typography textAlign="center">{page.name}</Typography>
                                    </MenuItem>
                                ))}
                            </Menu>
                        </Box>
                        <AdbIcon sx={{ display: { xs: 'flex', md: 'none' }, mr: 1 }} />
                        <Typography
                            variant="h5"
                            noWrap
                            component="a"
                            href=""
                            sx={{
                            mr: 2,
                            display: { xs: 'flex', md: 'none' },
                            flexGrow: 1,
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: 'inherit',
                            textDecoration: 'none',
                            }}
                        >
                            LOGO
                        </Typography>
                        <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                            {pages.map((page) => (
                            <Button
                                key={page.name}
                                onClick={handleCloseNavMenu}
                                sx={{ my: 2, color: 'white', display: 'block' }}
                                component={Link} to={page.link}
                            >
                                {page.name}
                            </Button>
                            ))}
                        </Box>

                        <Button component={Link} variant='outlined' sx={dashboardButtonStyle} to="/dashboard">
                            Dashboard
                        </Button>

                        <Box sx={{ flexGrow: 0 }}>
                            <Tooltip title="Settings">
                                <IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
                                    <Avatar sx={{ bgcolor: deepOrange[500] }}>N</Avatar>
                                </IconButton>
                            </Tooltip>
                            <Menu
                                sx={{ mt: '45px' }}
                                id="menu-usersettings"
                                anchorEl={anchorElUser}
                                anchorOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                keepMounted
                                transformOrigin={{
                                    vertical: 'top',
                                    horizontal: 'right',
                                }}
                                open={settingsOpen}
                                onClose={handleCloseUserMenu}
                            >
                                {settings.map((setting) => (
                                    <MenuItem key={setting.name} onClick={() => { 
                                        setting.onClick && setting.onClick();
                                        setting.link && navigate(setting.link);
                                        handleCloseUserMenu();
                                    }}>
                                        <Typography textAlign="center">{setting.name}</Typography>
                                    </MenuItem>
                                ))}
                            </Menu>
                        </Box>
                    </Toolbar>
                </Container>
            </AppBar>
        </Box>
    );
};

export default Header;