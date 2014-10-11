//
//  AppDelegate.m
//  Kinder
//
//  Created by Klein Lieu on 10/11/14.
//  Copyright (c) 2014 Klein Lieu. All rights reserved.
//

#import "AppDelegate.h"
#import "MasterViewController.h"
#import "QuestionViewController.h"
#import "LeaderboardViewController.h" 

#import <XHTwitterPaggingViewer/XHTwitterPaggingViewer.h>

typedef NS_ENUM(NSInteger, ViewControllerIndex) {
    LeaderboardSection = 0,
    HomeViewSection = 1,
    QuestionSection = 2
};

static const NSString *kLeaderboardTitle = @"Leaderboard";
static const NSString *kHomeTitle = @"Home";
static const NSString *kQuestionTitle = @"Ask a Question";

@interface AppDelegate ()

@end

@implementation AppDelegate


- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
    // Override point for customization after application launch.
    
    self.window = [[UIWindow alloc] initWithFrame:[[UIScreen mainScreen] bounds]];
    
    XHTwitterPaggingViewer *twitterPaggingViewer = [[XHTwitterPaggingViewer alloc] init];
    
    NSMutableArray *viewControllers = [[NSMutableArray alloc] initWithCapacity:3];
    
    // Should match ViewControllerIndex order!
    NSArray *titles = @[kLeaderboardTitle, kHomeTitle, kQuestionTitle];
    
    [titles enumerateObjectsUsingBlock:^(NSString *title, NSUInteger idx, BOOL *stop) {
        UIViewController *viewController;
        if (idx == HomeViewSection) {
            viewController = [[MasterViewController alloc] initWithNibName:@"MasterViewController" bundle:nil];
        } else if (idx == QuestionSection) {
            viewController = [[QuestionViewController alloc] initWithNibName:@"QuestionViewController" bundle:nil];
        } else {
            viewController = [[LeaderboardViewController alloc] initWithNibName:@"LeaderboardViewController" bundle:nil];
        }
        viewController.title = title;
        UINavigationController *testNav = [[UINavigationController alloc] initWithRootViewController:viewController];
        [viewControllers addObject:testNav];
    }];
    
    twitterPaggingViewer.viewControllers = viewControllers;
    
    twitterPaggingViewer.didChangedPageCompleted = ^(NSInteger cuurentPage, NSString *title) {
        NSLog(@"cuurentPage : %ld on title : %@", (long)cuurentPage, title);
    };
    
    [twitterPaggingViewer setCurrentPage:HomeViewSection animated:YES];
    
    self.window.rootViewController = twitterPaggingViewer;
    
    [self.window makeKeyAndVisible];
    return YES;
}

- (void)applicationWillResignActive:(UIApplication *)application {
    // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
    // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
}

- (void)applicationDidEnterBackground:(UIApplication *)application {
    // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
    // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
}

- (void)applicationWillEnterForeground:(UIApplication *)application {
    // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
}

- (void)applicationDidBecomeActive:(UIApplication *)application {
    // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
}

- (void)applicationWillTerminate:(UIApplication *)application {
    // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
}

@end
